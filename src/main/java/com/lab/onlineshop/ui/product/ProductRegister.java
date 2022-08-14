package com.lab.onlineshop.ui.product;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.primefaces.model.file.UploadedFile;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
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
        saveImage();
        if(saveWithValidation(getEntity(),"Product type Created")){
            setEntity(new Product());
        }
    }

    @Transactional
    public void deleteSelectedProducts(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Product removed" : "Products removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        getEntity().setAvailableQuantity(null);
        getEntity().setProductType(null);
        getEntity().setRegisterDate(null);
        getEntity().setIsAvailable(false);
        getEntity().setPrice(BigDecimal.ZERO);
    }

    private void saveImage(){
        if(uploadedFile == null){
            return;
        }
        try(InputStream inputStream = uploadedFile.getInputStream()){
            getEntity().setImage(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadImage(Product product){
        byte[] image = product.getImage();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=image");
        response.setContentLength(image.length);
        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    @Override
    public List<Product> getEntitiesFromDataBase() {
        return getService().getProducts();
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
