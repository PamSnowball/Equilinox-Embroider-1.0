package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.component.IDeath;
import com.snowball.utils.Utils;
import com.snowball.embroider.enumerator.EnumEat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Food extends NativeComponent {
	boolean hasDeath = false;
	boolean isShare = false;
	
	EnumEat[] eat;
	
	IDeath[] death;
	
	int[] foodName;
	int[] foodPoints;
	
	int portions;
	
	public Food(EnumEat[] eat, int[] foodName, int[] foodPoints) {
		this.eat = eat;
		this.foodName = foodName;
		this.foodPoints = foodPoints;
	}
	
	public Food(IDeath[] death, EnumEat[] eat, int[] foodName, int[] foodPoints) {
		this.hasDeath = true;
		this.death = death;
		this.eat = eat;
		this.foodName = foodName;
		this.foodPoints = foodPoints;
	}

	public Food(IDeath[] death, EnumEat[] eat, int[] foodName, int[] foodPoints, int portions) {
		this.hasDeath = true;
		this.isShare = true;
		this.death = death;
		this.eat = eat;
		this.foodName = foodName;
		this.foodPoints = foodPoints;
		this.portions = portions;
	}
		
	public Collection<String> load(Entity entity) {
		List<String> food = new ArrayList<>();
		
		food.add(Utils.value("FOOD", eat.length));
		for (int i = 0; i < eat.length; i++)  {
			food.add(Utils.value(foodName, foodPoints, eat[i].name())); //GameText
			if (hasDeath && (eat[i].name().equals("WHOLE") || eat[i].name().equals("ROOT_VEG"))) {
				food.addAll(death[i].death());
			}
		
			else if (isShare && eat[i].name().equals("TO_SHARE")) food.add(portions + ";");
		}
		
		return food;
	}
	
	@Override
	public int getId() {
		return 32;
	}
}
