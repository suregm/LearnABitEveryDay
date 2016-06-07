package learn.TestGenericity.GenericityRestrict;

/**
 * Created by sure GM on 2016/6/5 17:08.
 */
public class Test2 {
	// 通配符"?"泛型
	private static void take(Genericity<?> animal) {
		//	private static void take(Genericity<>) {
		animal.print();
	}

	public static void main(String[] args) {
		Genericity<Dog> dog = new Genericity<Dog>(new Dog());
//		take(dog);  // private static void take(Animal animal) 泛型报错
//		take(dog);  // private static void take(Genericity<Dog> animal) 可以，但是方法限定在了Dog类，Cat类不能调用take(cat)
		take(dog);  // private static void take(Genericity<?> animal) ?通配符，不论Dog类、Cat类还是Animal类都适用

		Genericity<Cat> cat = new Genericity<Cat>(new Cat());
		take(cat);

		Genericity<Animal> animal = new Genericity<Animal>(new Animal());
		take(animal);
	}
}
