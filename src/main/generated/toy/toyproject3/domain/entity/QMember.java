package toy.toyproject3.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 478504609L;

    public static final QMember member = new QMember("member1");

    public final toy.toyproject3.domain.entity.auditing.QAuditingDate _super = new toy.toyproject3.domain.entity.auditing.QAuditingDate(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath loginid = createString("loginid");

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

