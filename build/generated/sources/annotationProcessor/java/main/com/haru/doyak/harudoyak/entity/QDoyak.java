package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoyak is a Querydsl query type for Doyak
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDoyak extends EntityPathBase<Doyak> {

    private static final long serialVersionUID = 1119681914L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoyak doyak = new QDoyak("doyak");

    public final QDoyakId doyakId;

    public final QMember member;

    public final QShareDoyak shareDoyak;

    public QDoyak(String variable) {
        this(Doyak.class, forVariable(variable), INITS);
    }

    public QDoyak(Path<? extends Doyak> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoyak(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoyak(PathMetadata metadata, PathInits inits) {
        this(Doyak.class, metadata, inits);
    }

    public QDoyak(Class<? extends Doyak> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doyakId = inits.isInitialized("doyakId") ? new QDoyakId(forProperty("doyakId")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.shareDoyak = inits.isInitialized("shareDoyak") ? new QShareDoyak(forProperty("shareDoyak"), inits.get("shareDoyak")) : null;
    }

}

