import java.util.*;

public class Category{
    private String name;
    private Category parentCategory;
    private Set<Category> childCategories=new HashSet<Category>();

    public Category(String name,Category parentCategory, Set<Category> childCategories) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.childCategories = childCategories;
    }

    public Category() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

    public void addChildCategory(Category category) {
	if (category == null)
	  throw new IllegalArgumentException("Can't add a null Category as child.");
	childCategories.add(category);
   }

    public String toString() {
      return name;     
    }

}
