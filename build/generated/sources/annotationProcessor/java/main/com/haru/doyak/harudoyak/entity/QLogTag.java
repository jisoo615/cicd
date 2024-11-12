package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogTag is a Querydsl query type for LogTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLogTag extends EntityPathBase<LogTag> {

    private static final long serialVersionUID = 578885236L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogTag logTag = new QLogTag("logTag");

    public final QLog log;

    public final QLogTagId logTagId;

    public final QTag tag;

    public QLogTag(String variable) {
        this(LogTag.class, forVariable(variable), INITS);
    }

    public QLogTag(Path<? extends LogTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogTag(PathMetadata metadata, PathInits inits) {
        this(LogTag.class, metadata, inits);
    }

    public QLogTag(Class<? extends LogTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.log = inits.isInitialized("log") ? new QLog(forProperty("log"), inits.get("log")) : null;
        this.logTagId = inits.isInitialized("logTagId") ? new QLogTagId(forProperty("logTagId")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
    }

}

