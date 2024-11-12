package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLetter is a Querydsl query type for Letter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLetter extends EntityPathBase<Letter> {

    private static final long serialVersionUID = 570068196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLetter letter = new QLetter("letter");

    public final DateTimePath<java.time.LocalDateTime> arrivedDate = createDateTime("arrivedDate", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> letterId = createNumber("letterId", Long.class);

    public final QLog log;

    public QLetter(String variable) {
        this(Letter.class, forVariable(variable), INITS);
    }

    public QLetter(Path<? extends Letter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLetter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLetter(PathMetadata metadata, PathInits inits) {
        this(Letter.class, metadata, inits);
    }

    public QLetter(Class<? extends Letter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.log = inits.isInitialized("log") ? new QLog(forProperty("log"), inits.get("log")) : null;
    }

}

