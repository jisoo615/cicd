package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 1374264673L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final BooleanPath isRemoved = createBoolean("isRemoved");

    public final QMember member;

    public final NumberPath<Long> parentCommentId = createNumber("parentCommentId", Long.class);

    public final QShareDoyak shareDoyak;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.shareDoyak = inits.isInitialized("shareDoyak") ? new QShareDoyak(forProperty("shareDoyak"), inits.get("shareDoyak")) : null;
    }

}

