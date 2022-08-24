package com.lab.onlineshop.ui.product;

import com.lab.onlineshop.api.util.UtilClass;
import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.model.UploadedAppFile;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.primefaces.model.file.UploadedFile;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class ProductRegister extends RegisterForm<Product, ProductService> {

    private UploadedFile uploadedFile;

    @Inject
    private ProductTypeService productTypeService;

    @Transactional
    public void saveProduct(){
        getFormEntity().setFile(saveImage());
        if(saveWithValidation(getFormEntity(),"Product type Created")){
            setFormEntity(new Product());
        }
    }

    @Transactional
    public void deleteSelectedProducts(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Product removed" : "Products removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        getFormEntity().setAvailableQuantity(null);
        getFormEntity().setProductType(null);
        getFormEntity().setRegisterDate(null);
        getFormEntity().setIsAvailable(false);
        getFormEntity().setPrice(BigDecimal.ZERO);
    }

    @Transactional
    private UploadedAppFile saveImage(){
        if(uploadedFile == null){
            return null;
        }
        UploadedAppFile file = new UploadedAppFile();
        file.setData(uploadedFile.getContent());
        file.setName(uploadedFile.getFileName());
        file.setMime(uploadedFile.getContentType());
        saveEntity(file);
        return file;
    }

    public void downloadImage(Product product){
        UploadedAppFile uploadedAppFile = product.getFile();
        boolean success = UtilClass.downloadFile(FacesContext.getCurrentInstance(), uploadedAppFile);
        if(!success){
            showWarningMessage("There is not image to download");
        }

    }

    @Override
    public List<Product> getEntitiesFromDataBase() {
        return getFormEntityService().getProducts();
    }
    public List<ProductType> getProductsType(){
        return productTypeService.getProductsType();
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
