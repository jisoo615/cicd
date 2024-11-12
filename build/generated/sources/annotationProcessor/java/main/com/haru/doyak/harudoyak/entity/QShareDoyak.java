package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShareDoyak is a Querydsl query type for ShareDoyak
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShareDoyak extends EntityPathBase<ShareDoyak> {

    private static final long serialVersionUID = -1621482729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShareDoyak shareDoyak = new QShareDoyak("shareDoyak");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final ListPath<Doyak, QDoyak> doyaks = this.<Doyak, QDoyak>createList("doyaks", Doyak.class, QDoyak.class, PathInits.DIRECT2);

    public final QFile file;

    public final QMember member;

    public final NumberPath<Long> shareDoyakId = createNumber("shareDoyakId", Long.class);

    public final StringPath title = createString("title");

    public QShareDoyak(String variable) {
        this(ShareDoyak.class, forVariable(variable), INITS);
    }

    public QShareDoyak(Path<? extends ShareDoyak> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShareDoyak(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShareDoyak(PathMetadata metadata, PathInits inits) {
        this(ShareDoyak.class, metadata, inits);
    }

    public QShareDoyak(Class<? extends ShareDoyak> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

