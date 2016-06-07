package learn.TestGenericity.GenericityRestrict;

/**
 * Created by sure GM on 2016/6/5 16:53.
 */
public class Test {

	public static void main(String[] args) {
		Genericity<Dog> genericity = new Genericity<Dog>(new Dog());
		Dog dog = genericity.getOb();
		dog.print();

		Genericity<Cat> genericity2 = new Genericity<Cat>(new Cat());
		Cat cat = genericity2.getOb();
		cat.print();

		Genericity<Animal> genericity3 = new Genericity<Animal>(new Animal());

		// Integer类则不符合
//		Genericity<Integer> genericity4 = new Genericity<Integer>(2);

	}
}