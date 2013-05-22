package it.univaq.mwt.j2ee.kmZero.business.service;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.model.Image;

public interface ImageService {
	
	void createImage(Image image) throws BusinessException;
	
	void updateImage(Image image) throws BusinessException;
	
	void deleteImage(long oid_image) throws BusinessException;
	
}
