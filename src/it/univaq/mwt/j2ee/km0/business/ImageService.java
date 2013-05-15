package it.univaq.mwt.j2ee.km0.business;

import it.univaq.mwt.j2ee.km0.business.model.Image;

public interface ImageService {
	
	void createImage(Image image) throws BusinessException;
	
	void updateImage(Image image) throws BusinessException;
	
	void deleteImage(long oid_image) throws BusinessException;
	
}
