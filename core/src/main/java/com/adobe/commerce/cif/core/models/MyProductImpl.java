package com.adobe.commerce.cif.core.models;

import com.adobe.cq.commerce.core.components.models.common.Price;
import com.adobe.cq.commerce.core.components.models.product.*;
import com.adobe.cq.commerce.core.components.models.retriever.AbstractProductRetriever;
import com.adobe.cq.commerce.magento.graphql.MediaGalleryEntry;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Model(
        adaptables = {SlingHttpServletRequest.class}
)
public class MyProductImpl  {

    @Self
    private Product product;

   
    public Boolean getFound() {
        return product.getFound();
    }

    public String getName() {
        return product.getName();
    }

    public String getDescription() {
        return product.getDescription();
    }

    public String getSku() {
        return product.getSku();
    }

    public String getCurrency() {
        return product.getCurrency();
    }

    
    public Double getPrice() {
        return product.getPrice();
    }

    
    public Price getPriceRange() {
        return product.getPriceRange();
    }

    
    public String getFormattedPrice() {
        return product.getFormattedPrice();
    }

    
    public Boolean getInStock() {
        return product.getInStock();
    }

    
    public Boolean isConfigurable() {
        return product.isConfigurable();
    }

    
    public Boolean isGroupedProduct() {
        return product.isGroupedProduct();
    }

    
    public Boolean isVirtualProduct() {
        return product.isVirtualProduct();
    }

    
    public String getVariantsJson() {
        return product.getVariantsJson();
    }

    
    public List<Variant> getVariants() {
        return product.getVariants();
    }

    
    public List<GroupItem> getGroupedProductItems() {
        return product.getGroupedProductItems();
    }

    
    public List<Asset> getAssets() {
        return  this.filterAndSortAssets(product.getProductRetriever().fetchProduct().getMediaGalleryEntries());

    }

    private List<Asset> filterAndSortAssets(List<MediaGalleryEntry> assets) {
        return (List)assets.parallelStream().filter((e) -> {
            return !e.getDisabled() && e.getMediaType().equals("image");
        }).map(this::mapAsset).sorted(Comparator.comparing(Asset::getPosition)).collect(Collectors.toList());
    }

    private Asset mapAsset(MediaGalleryEntry entry) {
        MyAssetImpl asset = new MyAssetImpl();
        asset.setLabel(entry.getLabel());
        asset.setPosition(entry.getPosition());
        asset.setType(entry.getMediaType());
        asset.setPath(entry.getFile());
        return asset;
    }

    
    public String getAssetsJson() {
        return product.getAssetsJson();
    }

    
    public List<VariantAttribute> getVariantAttributes() {
        return product.getVariantAttributes();
    }

    
    public Boolean loadClientPrice() {
        return product.loadClientPrice();
    }

    
    public AbstractProductRetriever getProductRetriever() {
        return product.getProductRetriever();
    }
}
