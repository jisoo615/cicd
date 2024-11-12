package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDoyakId is a Querydsl query type for DoyakId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDoyakId extends BeanPath<DoyakId> {

    private static final long serialVersionUID = -2022469579L;

    public static final QDoyakId doyakId = new QDoyakId("doyakId");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> shareDoyakId = createNumber("shareDoyakId", Long.class);

    public QDoyakId(String variable) {
        super(DoyakId.class, forVariable(variable));
    }

    public QDoyakId(Path<? extends DoyakId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoyakId(PathMetadata metadata) {
        super(DoyakId.class, metadata);
    }

}

