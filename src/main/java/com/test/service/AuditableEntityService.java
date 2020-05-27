package com.test.service;

import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.entity.AuditableEntity;
import com.test.entity.AuditableEntityStatus;
import com.test.exception.BusinessException;

public interface AuditableEntityService<ENTITY extends AuditableEntity> {

	Logger logger = LoggerFactory.getLogger(AuditableEntityService.class);

	ENTITY save(ENTITY entity);

	ENTITY findById(Long id);

	default Long createAuditableEntity(ENTITY entity) {
		entity.setArchived(Boolean.FALSE);
		entity.setAuditableStatus(AuditableEntityStatus.CREATED);
		entity.setLastModifiedDate(new Date());
		ENTITY result = save(entity);
		return result.getId();
	}

	default Long updateAuditableEntity(ENTITY entity) {
		Long previousId = entity.getId();

		if (getEntityManager().contains(entity)) {
			// in case entity is in persistent state - transient copy should be created.
			// Otherwise save will fail
			// saying that id was altered. Simply detaching entity from entityManager will
			// not work
			// in case there are OneToMany relations.
			logger.info("Auditable entity is already persistent, creating a transient clone...");
			entity = createTransientClone(entity);
		}

		archiveEntity(previousId);
		entity.setId(null);
		entity.setArchived(Boolean.FALSE);
		entity.setAuditableStatus(AuditableEntityStatus.UPDATED);
		entity.setLastModifiedDate(new Date());
		ENTITY previousEntity = findById(previousId);

		if (previousEntity.getChainId() == null)
			entity.setChainId(previousId);
		else
			entity.setChainId(previousEntity.getChainId());

		ENTITY result = save(entity);
		return result.getId();
	}

	default ENTITY createTransientClone(ENTITY entity) {
		throw new NotImplementedException("Please implement entity transient state copy creation.");
	}

	EntityManager getEntityManager();

	default void deleteAuditableEntity(final Long entityId) {
		final ENTITY entity = findById(entityId);
		entity.setAuditableStatus(AuditableEntityStatus.DELETED);
		archiveEntity(entity);
		save(entity);
	}

	private void archiveEntity(Long id) {
		archiveEntity(findById(id));
	}

	private void archiveEntity(ENTITY entity) {
		if (Boolean.TRUE.equals(entity.getArchived())) {
			throw new BusinessException("Modified already archived entity with id = " + entity.getId());
		}
		entity.setArchived(Boolean.TRUE);
		save(entity);
	}
}
