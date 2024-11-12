package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogTagId is a Querydsl query type for LogTagId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLogTagId extends BeanPath<LogTagId> {

    private static final long serialVersionUID = -2037034321L;

    public static final QLogTagId logTagId = new QLogTagId("logTagId");

    public final NumberPath<Long> logId = createNumber("logId", Long.class);

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public QLogTagId(String variable) {
        super(LogTagId.class, forVariable(variable));
    }

    public QLogTagId(Path<? extends LogTagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogTagId(PathMetadata metadata) {
        super(LogTagId.class, metadata);
    }

}

