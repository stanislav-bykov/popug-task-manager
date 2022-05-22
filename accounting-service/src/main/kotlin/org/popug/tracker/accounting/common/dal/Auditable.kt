package org.popug.tracker.accounting.common.dal

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Auditable {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var created: OffsetDateTime = OffsetDateTime.now()

    @LastModifiedDate
    @Column(name = "updated_at")
    var modified: OffsetDateTime = OffsetDateTime.now()
}
