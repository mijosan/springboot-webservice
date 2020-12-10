package com.toyproject.springbootwebservice.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * 모든 Entity의 상위클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리함
 */

@Getter
@MappedSuperclass // JPA Entity 클래스들이 상속할 경우 필드들도 칼럼으로 인식하록 함
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 포함
public abstract class BaseTimeEntity {
    
    @CreatedDate // Entity가 생성될떄 시간이 자동 저장
    private LocalDateTime CreatedDate;

    @LastModifiedDate // Entity가 변경될떄 시간이 자동 저장
    private LocalDateTime modifiedDate;

}
