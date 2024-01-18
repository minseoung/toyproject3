package toy.toyproject3.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUploadFile extends BeanPath<UploadFile> {

    private static final long serialVersionUID = 970198948L;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final StringPath dbFileName = createString("dbFileName");

    public final StringPath userFileName = createString("userFileName");

    public QUploadFile(String variable) {
        super(UploadFile.class, forVariable(variable));
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadFile(PathMetadata metadata) {
        super(UploadFile.class, metadata);
    }

}

