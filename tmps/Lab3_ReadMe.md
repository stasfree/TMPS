# Лабораторная работа №3
_Подготовил студент группы TI-164, Фридман Станислав_

## Задание 💠
Целью данной лабораторной работы было имплементировать 5 паттернов.

## Использованные паттерны 📓
1. _Iterator_
2. _MVC_
3. _Strategy_
4. _TemplateMethod_
5. _Visitor_

## _Iterator_
![Iterator](https://refactoring.guru/images/patterns/cards/iterator-mini-2x.png)

Итератор — это поведенческий паттерн проектирования, который даёт возможность последовательно обходить элементы составных объектов, не раскрывая их внутреннего представления.

Идея паттерна Итератор состоит в том, чтобы вынести поведение обхода коллекции из самой коллекции в отдельный класс.

![Iterator](https://refactoring.guru/images/patterns/diagrams/iterator/solution1-2x.png)

Иттератор дает нам возможность выполнить реализацию разных проходов по листу 

## _MVC_

Образец MVC расшифровывается как Model-View-Controller Pattern. Этот шаблон используется для разделения проблем приложения.

Модель - Модель представляет объект или JAVA POJO, несущий данные. Он также может иметь логику для обновления контроллера, если его данные изменяются.

Представление - представление представляет собой визуализацию данных, содержащихся в модели.

Контроллер - Контроллер действует как на модель, так и на вид. Он контролирует поток данных в объект модели и обновляет представление при каждом изменении данных. Он сохраняет вид и модель отдельно.

## _Strategy_

В паттерне Стратегия поведение класса или его алгоритм могут быть изменены во время выполнения. Этот тип шаблона проектирования подпадает под модель поведения.

В паттерне «Стратегия» мы создаем объекты, представляющие различные стратегии, и объект контекста, поведение которого меняется в зависимости от объекта стратегии. Объект стратегии изменяет алгоритм выполнения объекта контекста.


## _TemplateMethod_

В шаблоне Template Method абстрактный класс предоставляет определенный способ (ы) / шаблон (ы) для выполнения своих методов. Его подклассы могут переопределять реализацию метода по мере необходимости, но вызов должен быть таким же, как определено абстрактным классом. Этот шаблон относится к категории шаблонов поведения.

## _Visitor_

В шаблоне Visitor мы используем класс посетителя, который изменяет алгоритм выполнения класса элемента. Таким образом, алгоритм выполнения элемента может меняться как и когда меняется посетитель. Этот шаблон относится к категории шаблонов поведения. Согласно шаблону объект-элемент должен принимать объект-посетитель, чтобы объект-посетитель выполнял операцию над объектом-элементом.


# _Lab3_TMPS_Iterator_

Создаем интерфейсы:

```java
public interface Iterator {
   public boolean hasNext();
   public Object next();
}

public interface Container {
   public Iterator getIterator();
}
```

Создаем конкретный класс, реализующий интерфейс Контейнера . Этот класс имеет внутренний класс NameIterator, реализующий интерфейс Iterator .

```java
public class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}
```

Используем NameRepository, чтобы получить итератор и вывести имена:

```java
public class IteratorPatternDemo {
	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 	
   }
}
```

Запуск этого кода:

```java
Name : Robert
Name : John
Name : Julie
Name : Lora
```

# _Lab3_TMPS_MVC_

Создаем модель.

```java
public class Student {
   private String rollNo;
   private String name;
   
   public String getRollNo() {
      return rollNo;
   }
   
   public void setRollNo(String rollNo) {
      this.rollNo = rollNo;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
}
```

Создаем представление:

```java
public class StudentView {
   public void printStudentDetails(String studentName, String studentRollNo){
      System.out.println("Student: ");
      System.out.println("Name: " + studentName);
      System.out.println("Roll No: " + studentRollNo);
   }
}
```

Создаем контроллер:

```java
public class StudentController {
   private Student model;
   private StudentView view;

   public StudentController(Student model, StudentView view){
      this.model = model;
      this.view = view;
   }

   public void setStudentName(String name){
      model.setName(name);		
   }

   public String getStudentName(){
      return model.getName();		
   }

   public void setStudentRollNo(String rollNo){
      model.setRollNo(rollNo);		
   }

   public String getStudentRollNo(){
      return model.getRollNo();		
   }

   public void updateView(){				
      view.printStudentDetails(model.getName(), model.getRollNo());
   }	
}
```

Используем методы StudentController, чтобы продемонстрировать использование шаблона проектирования MVC.

```java
public class MVCPatternDemo {
   public static void main(String[] args) {

      //fetch student record based on his roll no from the database
      Student model  = retriveStudentFromDatabase();

      //Create a view : to write student details on console
      StudentView view = new StudentView();

      StudentController controller = new StudentController(model, view);

      controller.updateView();

      //update model data
      controller.setStudentName("John");

      controller.updateView();
   }

   private static Student retriveStudentFromDatabase(){
      Student student = new Student();
      student.setName("Robert");
      student.setRollNo("10");
      return student;
   }
}
```

После запуска кода получаем следующее:

```java
Student: 
Name: Robert
Roll No: 10
Student: 
Name: John
Roll No: 10
```

# _Lab3_TMPS_Strategy_

Создаем интерфейс.

```java
public interface Strategy {
   public int doOperation(int num1, int num2);
}
```

Создаем конкретные классы, реализующие тот же интерфейс.

```java
public class OperationAdd implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 + num2;
   }
}

public class OperationSubstract implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 - num2;
   }
}

public class OperationMultiply implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }
}
```

Создаем контекстный класс.

```java
public class Context {
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeStrategy(int num1, int num2){
      return strategy.doOperation(num1, num2);
   }
}
```

Используем контекст, чтобы увидеть изменения в поведении, когда он меняет свою стратегию .

```java
public class StrategyPatternDemo {
   public static void main(String[] args) {
      Context context = new Context(new OperationAdd());		
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationSubstract());		
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationMultiply());		
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
   }
}
```

После запуска кода получаем следующее

```java
10 + 5 = 15
10 - 5 = 5
10 * 5 = 50
```

# _Lab3_TMPS_TemplateMethod_

Создаем абстрактный класс с методом шаблона, являющимся окончательным.

```java
public abstract class Game {
   abstract void initialize();
   abstract void startPlay();
   abstract void endPlay();
   
   public final void play(){
      initialize();
      startPlay();
      endPlay();
   }
}
```

Создаем конкретные классы, расширяющие вышеуказанный класс.

```java
public class Cricket extends Game {

   @Override
   void endPlay() {
      System.out.println("Cricket Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Cricket Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Cricket Game Started. Enjoy the game!");
   }
}

public class Football extends Game {

   @Override
   void endPlay() {
      System.out.println("Football Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Football Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Football Game Started. Enjoy the game!");
   }
}
```

Используем шаблонный метод Game play (), чтобы продемонстрировать определенный способ игры.

```java
public class TemplatePatternDemo {
   public static void main(String[] args) {

      Game game = new Cricket();
      game.play();
      System.out.println();
      game = new Football();
      game.play();		
   }
}
```

После запуска кода получаем следующее

```java
Cricket Game Initialized! Start playing.
Cricket Game Started. Enjoy the game!
Cricket Game Finished!

Football Game Initialized! Start playing.
Football Game Started. Enjoy the game!
Football Game Finished!
```

# _Lab3_TMPS_Visitor_

Определяем интерфейс для представления элемента.

```java
public interface ComputerPart {
   public void accept(ComputerPartVisitor computerPartVisitor);
}
```

Создаем конкретные классы, расширяющие вышеуказанный интерфейс.

```java
public class Keyboard implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Monitor implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Mouse implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Computer implements ComputerPart {
	
   ComputerPart[] parts;

   public Computer(){
      parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};		
   } 


   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      for (int i = 0; i < parts.length; i++) {
         parts[i].accept(computerPartVisitor);
      }
      computerPartVisitor.visit(this);
   }
}
```

Определяем интерфейс для представления посетителя.

```java
public interface ComputerPartVisitor {
	public void visit(Computer computer);
	public void visit(Mouse mouse);
	public void visit(Keyboard keyboard);
	public void visit(Monitor monitor);
}
```

Создаем конкретного посетителя, реализующего вышеуказанный класс.

```java
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

   @Override
   public void visit(Computer computer) {
      System.out.println("Displaying Computer.");
   }

   @Override
   public void visit(Mouse mouse) {
      System.out.println("Displaying Mouse.");
   }

   @Override
   public void visit(Keyboard keyboard) {
      System.out.println("Displaying Keyboard.");
   }

   @Override
   public void visit(Monitor monitor) {
      System.out.println("Displaying Monitor.");
   }
}
```

Используем ComputerPartDisplayVisitor для отображения частей компьютера

```java
public class VisitorPatternDemo {
   public static void main(String[] args) {

      ComputerPart computer = new Computer();
      computer.accept(new ComputerPartDisplayVisitor());
   }
}
```

После запуска кода получаем следующее

```java
Displaying Mouse.
Displaying Keyboard.
Displaying Monitor.
Displaying Computer.
```