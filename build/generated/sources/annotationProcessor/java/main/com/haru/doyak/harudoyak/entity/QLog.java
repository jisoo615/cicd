package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLog is a Querydsl query type for Log
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLog extends EntityPathBase<Log> {

    private static final long serialVersionUID = -655809722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLog log = new QLog("log");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final StringPath emotion = createString("emotion");

    public final QFile file;

    public final BooleanPath isRead = createBoolean("isRead");

    public final NumberPath<Long> logId = createNumber("logId", Long.class);

    public final QMember member;

    public final ListPath<LogTag, QLogTag> tags = this.<LogTag, QLogTag>createList("tags", LogTag.class, QLogTag.class, PathInits.DIRECT2);

    public QLog(String variable) {
        this(Log.class, forVariable(variable), INITS);
    }

    public QLog(Path<? extends Log> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLog(PathMetadata metadata, PathInits inits) {
        this(Log.class, metadata, inits);
    }

    public QLog(Class<? extends Log> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

