package toy.toyproject3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.FileStore;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.entity.UploadFile;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.domain.repository.MemberRepository;
import toy.toyproject3.exception.BoardNotFoundException;
import toy.toyproject3.exception.MemberNotFound;
import toy.toyproject3.web.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    public Long post(BoardAddRequest addRequest, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            List<MultipartFile> images = addRequest.getImages();
            List<UploadFile> uploadFiles = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    uploadFiles.add(fileStore.saveFile(image));
                }
            }
            Board board = new Board(addRequest.getTitle(), addRequest.getContent(), member, uploadFiles);
            boardRepository.save(board);
            return board.getId();
        } else {
            throw new MemberNotFound("존재하지 않는 회원입니다.");
        }
    }
    public Page<BoardsResponse> findBoards(BoardsRequest boardsRequest, Pageable pageable) {
        List<Board> boards = boardRepository.findBoards(boardsRequest.getTitle(), boardsRequest.getWriter());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boards.size());

        Page<Board> boardPage = new PageImpl<>(boards.subList(start, end), pageable, boards.size());
        Page<BoardsResponse> result = boardPage.map(board -> new BoardsResponse(board));
        return result;
    }

    public BoardResponse findBord(Long boardId, Long loginMemberId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            BoardResponse boardResponse = new BoardResponse(board, loginMemberId);
            return boardResponse;
        } else {
            throw new BoardNotFoundException("해당 게시물을 찾을 수 없습니다.");
        }
    }

    public BoardEditResponse editForm(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return new BoardEditResponse(board);
        } else {
            throw new BoardNotFoundException("해당 게시물을 찾을 수 없습니다.");
        }
    }

    public void edit(Long id, BoardEditResponse editRequest) {
        log.info("edit실행");
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            List<MultipartFile> images = editRequest.getFiles();
            List<UploadFile> uploadFiles = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    uploadFiles.add(fileStore.saveFile(image));
                }
            }
            board.edit(editRequest.getTitle(), editRequest.getContent(), uploadFiles);
        } else {
            throw new BoardNotFoundException("해당 게시물을 찾을 수 없습니다.");
        }
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
