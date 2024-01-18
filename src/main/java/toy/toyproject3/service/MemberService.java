package toy.toyproject3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import toy.toyproject3.domain.entity.FileStore;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.entity.UploadFile;
import toy.toyproject3.domain.repository.MemberRepository;
import toy.toyproject3.exception.AlreadyExistLoginIdException;
import toy.toyproject3.exception.LoginFailedException;
import toy.toyproject3.exception.MemberNotFound;
import toy.toyproject3.web.dto.*;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    public Long join(MemberAddRequest addRequest) {
        if (memberRepository.findByLoginid(addRequest.getLoginid()).isPresent()) {
            throw new AlreadyExistLoginIdException("이미 존재하는 아이디 입니다.");
        }
//        UploadFile uploadFile = fileStore.saveFile(addRequest.getPfp());
        Member member = new Member(addRequest.getName(), addRequest.getAge(), addRequest.getLoginid(), addRequest.getPw());
        memberRepository.save(member);
        return member.getId();
    }

    public Member findMember(Long id) {
        if (memberRepository.findById(id).isPresent()) {
            Member findMember = memberRepository.findById(id).get();
            return findMember;
        } else {
            throw new MemberNotFound("해당 회원을 찾을 수 없습니다.");
        }
    }

    public MemberResponse member(Long id, Pageable pageable) {
        Member findMember = this.findMember(id);
        MemberResponse memberResponse = new MemberResponse(findMember, pageable);
        return memberResponse;
    }

    public Long delete(Long id) {
        memberRepository.deleteById(id);
        return id;
    }

    public Long login(LoginRequest request) {
        Optional<Member> optionalMember = memberRepository.findByLoginid(request.getLoginid());
        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            //이어서 비밀번호도 확인
            if (findMember.getPw().equals(request.getPw())) {
                return findMember.getId();
            } else {
                throw new LoginFailedException("입력하신 아이디 또는 비밀번호가 올바르지 않습니다.");
            }
        } else {
            throw new LoginFailedException("입력하신 아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    public void logout(Long id) {

    }

    public Page<Member> findMembers(MembersRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Page<Member> page = memberRepository.findAll(pageRequest);
        return page;
    }

    public Page<MembersWithBoardsResponse> findMembersWithBoards(MembersRequest request) {
        Page<Member> page = this.findMembers(request);
        Page<MembersWithBoardsResponse> result = page.map(member -> new MembersWithBoardsResponse(member));
        return result;
    }

    public MemberEditResponse editForm(Long id) {
        Member findMember = this.findMember(id);
        MemberEditResponse response = new MemberEditResponse(findMember);
        return response;
    }

    public void edit(MemberEditResponse response, Long id, Long loginMemberId) {
        if (!Objects.equals(id, loginMemberId)) {
            throw new RuntimeException("본인만 가능합니다");
        } else {
            UploadFile uploadFile = fileStore.saveFile(response.getFile());
            response.setPfp(uploadFile);
            Member member = this.findMember(id);
            member.edit(response);
        }
    }
}
