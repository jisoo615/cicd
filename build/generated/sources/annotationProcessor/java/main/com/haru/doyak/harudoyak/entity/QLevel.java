package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLevel is a Querydsl query type for Level
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLevel extends EntityPathBase<Level> {

    private static final long serialVersionUID = 1126769414L;

    public static final QLevel level = new QLevel("level");

    public final DatePath<java.time.LocalDate> firstDate = createDate("firstDate", java.time.LocalDate.class);

    public final NumberPath<Long> levelId = createNumber("levelId", Long.class);

    public final NumberPath<Long> logCount = createNumber("logCount", Long.class);

    public final DatePath<java.time.LocalDate> logLastDate = createDate("logLastDate", java.time.LocalDate.class);

    public final NumberPath<Long> maxContinuity = createNumber("maxContinuity", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final NumberPath<Long> recentContinuity = createNumber("recentContinuity", Long.class);

    public final NumberPath<Long> shareDoyakCount = createNumber("shareDoyakCount", Long.class);

    public QLevel(String variable) {
        super(Level.class, forVariable(variable));
    }

    public QLevel(Path<? extends Level> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLevel(PathMetadata metadata) {
        super(Level.class, metadata);
    }

}

