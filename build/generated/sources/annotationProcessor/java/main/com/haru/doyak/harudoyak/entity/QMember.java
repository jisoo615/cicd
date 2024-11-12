package com.haru.doyak.harudoyak.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 598471512L;

    public static final QMember member = new QMember("member1");

    public final StringPath aiNickname = createString("aiNickname");

    public final ListPath<Doyak, QDoyak> doyaks = this.<Doyak, QDoyak>createList("doyaks", Doyak.class, QDoyak.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final StringPath goalName = createString("goalName");

    public final BooleanPath isVerified = createBoolean("isVerified");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    public final StringPath refreshToken = createString("refreshToken");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

