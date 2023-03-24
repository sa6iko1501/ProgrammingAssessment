package org.Main.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Main.Enums.MarkupType;

@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String Name;
    private double cost;
    private MarkupType markupType;
    private Promotion promotion;
    private double markup;
    private int volume;

    public Product(String name, double cost, MarkupType markupType, Promotion promotion, double markup, int volume){
        if(name==null){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(cost<=0){
            throw new IllegalArgumentException("Cost cannot be lower or equal to 0");
        }
        if(markupType==null){
            throw new IllegalArgumentException("Markup Type cannot be null");
        }
        if(markup<=0){
            throw new IllegalArgumentException("Markup cannot be lower or equal to 0");
        }
        if(volume < 0) {
            throw new IllegalArgumentException("Volume cannot be lower than 0");
        }
        this.Name = name;
        this.cost = cost;
        this.markupType = markupType;
        this.promotion = promotion;
        this.markup = markup;
        this.volume = volume;
    }
    public void setCost(double cost) {
        if(cost<=0){
            throw new IllegalArgumentException("Cost cannot be set to a negative value or 0");
        }
        this.cost = cost;
    }

    public void setMarkup(double markup) {
        if(markup<=0){
            throw new IllegalArgumentException("Markup cannot be set to a negative value or 0");
        }
        this.markup = markup;
    }

    public void setVolume(int volume) {
        if(volume<0){
            throw new IllegalArgumentException("Volume cannot be set to a negative value");
        }
        this.volume = volume;
    }

    public void setMarkupType(MarkupType markupType){
        if(markupType==null){
            throw new IllegalArgumentException("Markup type cannot be set to null");
        }
        this.markupType = markupType;
    }
}
