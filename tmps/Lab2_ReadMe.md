# Лабораторная работа №2
_Подготовил студент группы TI-164, Фридман Станислав_

## Задание 💠
Целью данной лабораторной работы было имплементировать 5 структурных паттернов.

## Использованные структурные паттерны 📓
1. _Adapter_
2. _Bridge_
3. _Composite_
4. _Decorator_
5. _Filter_

## _Adapter_
Адаптер — это структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе.

![alt text](https://refactoring.guru/images/patterns/content/adapter/adapter.png "Logo Title Text 1")

## _Bridge_
Мост — это структурный паттерн проектирования, который разделяет один или несколько классов на две отдельные иерархии — абстракцию и реализацию, позволяя изменять их независимо друг от друга.

![alt text](https://refactoring.guru/images/patterns/content/bridge/bridge.png "Logo Title Text 1")

## _Composite_

Компоновщик (англ. Composite pattern) — структурный шаблон проектирования, объединяющий объекты в древовидную структуру для представления иерархии от частного к целому. Компоновщик позволяет клиентам обращаться к отдельным объектам и к группам объектов одинаково. Паттерн определяет иерархию классов, которые одновременно могут состоять из примитивных и сложных объектов, упрощает архитектуру клиента, делает процесс добавления новых видов объекта более простым.

![alt text](https://refactoring.guru/images/patterns/content/composite/composite.png "Logo Title Text 1")

## _Decorator_
![Decorator](https://refactoring.guru/images/patterns/cards/decorator-mini-2x.png)

Декоратор — это структурный паттерн проектирования, который позволяет динамически добавлять объектам новую функциональность, оборачивая их в полезные «обёртки».
Декоратор имеет альтернативное название — обёртка. Оно более точно описывает суть паттерна: вы помещаете целевой объект в другой объект-обёртку, который запускает базовое поведение объекта, а затем добавляет к результату что-то своё.

Оба объекта имеют общий интерфейс, поэтому для пользователя нет никакой разницы, с каким объектом работать — чистым или обёрнутым. Вы можете использовать несколько разных обёрток одновременно — результат будет иметь объединённое поведение всех обёрток сразу.

С помощью декоратор мы оборачиваем функцию в другую с дополнительным функционалом 

## _Filter_

Filter или Criteria паттер - позволяет разработчикам фильтровать набор объектов, используя различные критерии и разъединяя их с помощью логических операций. Этот тип шаблона проектирования подпадает под структурный шаблон, так как этот шаблон объединяет несколько критериев для получения единых критериев.

#Lab2_TMPS_Adapter

Интерфейс Builder является нашим наиболее общим интерфейсом и предоставляет метод, который принимает тип здания и его местоположение:

```java
public interface Builder {  
    public void build(String type, String location);
}
```

Интерфейс AdvancedBuilder предоставляет два метода: один для строительства дома, а другой для строительства небоскреба:

```java
public interface AdvancedBuilder {  
    public void buildHouse(String location);
    public void buildSkyscrapper(String location);
}
```

Эти два интерфейса не связаны. Да, они разделяют тему, но они не связаны с кодом.

На этом этапе создается конкретный класс, реализующий интерфейс AdvancedBuilder :

```java
public class HouseBuilder implements AdvancedBuilder {  
    @Override
    public void buildHouse(String location) {
        System.out.println("Building a house located in the " + location + "area!");
    }

    @Override
    public void buildSkyscrapper(String location) {
        //don't implement
    }
}
```

И, конечно же, по той же аналогии создается еще один конкретный класс:

```java
public class SkyscrapperBuilder implements AdvancedBuilder {  
    @Override
    public void buildSkyscrapper(String location) {
        System.out.println("Building a skyscrapper in the " + location + "area!");
    }

    @Override
    public void buildHouse(String location) {
        //don't implement
    }
}
```

Вот часть адаптера - для соединения этих двух интерфейсов создается BuilderAdapter реализующий Builder :

```java
public class BuilderAdapter implements Builder {  
    AdvancedBuilder advancedBuilder;

    public BuilderAdapter(String type) {
        if(type.equalsIgnoreCase("House")) {
            advancedBuilder = new HouseBuilder();
        } else if(type.equalsIgnoreCase("Skyscrapper")) {
            advancedBuilder = new SkyscrapperBuilder();
        }
    }

    @Override
    public void build(String type, String location) {
        if(type.equalsIgnoreCase("House")) {
            advancedBuilder.buildHouse(location);
        } else if(type.equalsIgnoreCase("Skyscrapper")) {
            advancedBuilder.buildSkyscrapper(location);
        }
    }
}
```

Работая с адаптером, мы наконец можем реализовать решение и использовать метод интерфейса Builder с BuilderAdapter для построения поддерживаемых типов зданий.

```java
public class BuilderImplementation implements Builder {  
    BuilderAdapter builderAdapter;

    @Override
    public void build(String type, String location) {
        if(type.equalsIgnoreCase("House") || type.equalsIgnoreCase("Skyscrapper")) {
            builderAdapter = new BuilderAdapter(type);
            builderAdapter.build(type, location);
        } else {
            System.out.println("Invalid building type.");
        }
    }
}
```

И наблюдать за результатом:

```java
public class Main {  
    public static void main(String[] args) {
        BuilderImplementation builderImpl = new BuilderImplementation();

        builderImpl.build("house", "Downtown");
        builderImpl.build("Skyscrapper", "City Center");
        builderImpl.build("Skyscrapper", "Outskirts");
        builderImpl.build("Hotel", "City Center");
    }
}
```

Выполнение приведенного выше кода даст:

```java
Building a house located in the Downtown area!  
Building a skyscrapper in the City Center area!  
Building a skyscrapper in the Outskirts area!  
Invalid building type.  
```

#Lab2_TMPS_Bridge

Как обычно, интерфейс является отправной точкой:

```java
public interface FeedingAPI {  
    public void feed(int timesADay, int amount, String typeOfFood);
}
```

После чего два конкретных класса реализуют это:

```java
public class BigDog implements FeedingAPI {  
    @Override
    public void feed(int timesADay, int amount, String typeOfFood) {
        System.out.println("Feeding a big dog, " + timesADay + " times a day with " + 
            amount + " g of " + typeOfFood);
    }
}

public class SmallDog implements FeedingAPI {  
    @Override
    public void feed(int timesADay, int amount, String typeOfFood) {
        System.out.println("Feeding a small dog, " + timesADay + " times a day with " + 
            amount + " g of " + typeOfFood);
    }
}
```

Используя интерфейс FeedingAPI , создается абстрактный класс Animal :

```java
public abstract class Animal {  
    protected FeedingAPI feedingAPI;

    protected Animal(FeedingAPI feedingAPI) {
        this.feedingAPI = feedingAPI;
    }
    public abstract void feed();
}
```

Вот тут и вступает в силу паттерн Bridge. Создается класс bridge, который отделяет абстрактный класс Animal от его реализации:

```java
public class Dog extends Animal{  
    private int timesADay, amount;
    private String typeOfFood;

    public Dog(int timesADay, int amount, String typeOfFood, FeedingAPI feedingAPI) {
        super(feedingAPI);
        this.timesADay = timesADay;
        this.amount = amount;
        this.typeOfFood = typeOfFood;
    }

    public void feed() {
        feedingAPI.feed(timesADay, amount, typeOfFood);
    }
}
```

И наблюдаем за результатом:

```java
public class Main {  
    public static void main(String[] args) {
        Animal bigDog = new Dog(3, 500, "Meat", new BigDog());
        Animal smallDog = new Dog(2, 250, "Granules", new SmallDog());

        bigDog.feed();
        smallDog.feed();
    }
}
```

Запуск этого кода даст:

```java
Feeding a big dog, 3 times a day with 500 g of Meat  
Feeding a small dog, 2 times a day with 250 g of Granules  
```

#Lab2_TMPS_Composite

Давайте начнем с класса Employee . Этот класс будет создан несколько раз для формирования группы сотрудников:

```java
public class Employee {  
    private String name;
    private String position;
    private int wage;
    private List<Employee> coworkers;

    public Employee(String name, String position, int wage) {
        this.name = name;   
        this.position = position;
        this.wage = wage;
        coworkers = new ArrayList<Employee>();
    }

    public void addCoworker(Employee employee) {
        coworkers.add(employee);
    }

    public void removeCoworker(Employee employee) {
        coworkers.remove(employee);
    }

    public List<Employee> getCoworkers() {
        return coworkers;
    }

    public String toString() {
        return "Employee : | Name: " + name + ", Position: " + position + ", Wage: "
             + wage + " |";
    }
}
```

В классе есть список Employee , это наша группа объектов, которые мы хотим определить как отдельный объект.

```java
public class StackAbuseJavaDesignPatterns {  
    public static void main(String[] args) {
        Employee employee1 = new Employee("David", "Programmer", 1500);
        Employee employee2 = new Employee("Scott", "CEO", 3000);
        Employee employee3 = new Employee("Andrew", "Manager", 2000);
        Employee employee4 = new Employee("Scott", "Janitor", 500);
        Employee employee5 = new Employee("Juliette", "Marketing", 1000);
        Employee employee6 = new Employee("Rebecca", "Sales", 2000);
        Employee employee7 = new Employee("Chris", "Programmer", 1750);
        Employee employee8 = new Employee("Ivan", "Programmer", 1200);

        employee3.addCoworker(employee1);
        employee3.addCoworker(employee7);
        employee3.addCoworker(employee8);

        employee1.addCoworker(employee7);
        employee1.addCoworker(employee8);

        employee2.addCoworker(employee3);
        employee2.addCoworker(employee5);
        employee2.addCoworker(employee6);

        System.out.println(employee2);
        for (Employee headEmployee : employee2.getCoworkers()) {
            System.out.println(headEmployee);

            for(Employee employee : headEmployee.getCoworkers()) {
                System.out.println(employee);
            }
        }
    }
}
```

Здесь несколько сотрудников созданы. У генерального директора есть несколько сотрудников в качестве близких сотрудников, а у некоторых из них есть свои близкие сотрудники, занимающие более низкие должности.

В конце концов, старшие сотрудники являются близкими сотрудниками генерального директора, а обычные сотрудники - коллегами старших сотрудников.

Запуск приведенного выше кода даст:

```java
Employee : | Name: Scott, Position: CEO, Wage: 3000 |  
Employee : | Name: Andrew, Position: Manager, Wage: 2000 |  
Employee : | Name: David, Position: Programmer, Wage: 1500 |  
Employee : | Name: Chris, Position: Programmer, Wage: 1750 |  
Employee : | Name: Ivan, Position: Programmer, Wage: 1200 |  
Employee : | Name: Juliette, Position: Marketing, Wage: 1000 |  
Employee : | Name: Rebecca, Position: Sales, Wage: 2000 |  
```

#Lab2_TMPS_Decorator

В начале определим интерфейс:

```java
public interface Computer {  
    void assemble();    
}
```

И реализуя этот интерфейс, мы определим класс, который мы, используя шаблон Decorator, сделаем подверженным изменениям во время выполнения:

```java
public class BasicComputer implements Computer {  
    @Override
    public void assemble() {
        System.out.print("Assembling a basic computer.");
    }
}
```

Теперь для класса декоратора:

```java
public abstract class ComputerDecorator implements Computer {  
    protected Computer computer;

    public ComputerDecorator(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void assemble() {
        this.computer.assemble();
    }
}
```

Наши конкретные классы расширят этот класс, унаследовав его функциональность и добавив свои собственные функции в процесс:

```java
public class GamingComputer extends ComputerDecorator {  
    public GamingComputer(Computer computer) {
        super(computer);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding characteristics of a gaming computer! ");
    }
}

public class WorkComputer extends ComputerDecorator {  
    public WorkComputer(Computer computer) {
        super(computer);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding characteristics of a work computer! ");
    }
}
```

Когда эти конкретные классы полностью определены, мы можем наблюдать результат:

```java
public class Main {  
    public static void main(String[] args) {
        Computer gamingComputer = new GamingComputer(new BasicComputer());
        gamingComputer.assemble();
        System.out.println("\n");

        Computer workComputer = new WorkComputer(new GamingComputer(new 
            BasicComputer()));
        workComputer.assemble();
    }
}
```

Запуск этого кода даст:

```java
Assembling a basic computer. Adding characteristics of a gaming computer! 

Assembling a basic computer. Adding characteristics of a gaming computer!  Adding characteristics of a work computer!  
```

#Lab2_TMPS_Filter

Начнем с класса Employee который мы будем фильтровать, используя различные Criteria :

```java
public class Employee {  
    private String name;
    private String gender;
    private String position;

    public Employee(String name, String gender, String position) {
        this.name = name;
        this.gender = gender;
        this.position = position;
    }
}
```

Интерфейс Criteria довольно прост, и все другие конкретные критерии будут реализовывать его метод по-своему:

```java
public interface Criteria {  
    public List<Employee> criteria(List<Employee> employeeList);
}
```

Основав систему фильтрации, давайте определим несколько различных критериев:

CriteriaMale - критерий для поиска сотрудников мужского пола
CriteriaFemale - критерий для поиска сотрудников женского пола
CriteriaSenior - критерий для поиска старших сотрудников
CriteriaJunior - критерий для поиска младших сотрудников
AndCriteria - критерий для поиска сотрудников, подходят двум критериям
OrCriteria - критерий для поиска сотрудников, которые соответствуют одному из критериев, которые мы применяем


Простой цикл for который добавляет всех сотрудников мужского пола в список и возвращает его.

```java
public class CriteriaMale implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> maleEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getGender().equalsIgnoreCase("Male")) {
                maleEmployees.add(employee);
            } 
        }
        return maleEmployees;
    }
}
```

Простой цикл for который добавляет всех сотрудников женского пола в список и возвращает его.

```java
public class CriteriaFemale implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> femaleEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getGender().equalsIgnoreCase("Female")) {
                femaleEmployees.add(employee);
            }
        }
        return femaleEmployees;
    }    
}
```

Аналогично:

```java
public class CriteriaSenior implements Criteria{

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
         List<Employee> seniorEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getPosition().equalsIgnoreCase("Senior")) {
                seniorEmployees.add(employee);
            }
        }
        return seniorEmployees;
    }    
}

public class CriteriaJunior implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
                 List<Employee> juniorEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getPosition().equalsIgnoreCase("Junior")) {
                juniorEmployees.add(employee);
            }
        }
        return juniorEmployees;
    } 
}
```

Список сотрудников фильтруется по первым критериям, а затем уже отфильтрованный список снова фильтруется по вторым критериям.

```java
public class AndCriteria implements Criteria {

    private Criteria firstCriteria;
    private Criteria secondCriteria;

    public AndCriteria(Criteria firstCriteria, Criteria secondCriteria) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
        return secondCriteria.criteria(firstCriteriaEmployees);
    }
}
```

Или по одному из критериев:

```java
private Criteria firstCriteria;
    private Criteria secondCriteria;

    public OrCriteria(Criteria firstCriteria, Criteria secondCriteria) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }


    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
        List<Employee> secondCriteriaEmployees = secondCriteria.criteria(employeeList);

        for (Employee employee : secondCriteriaEmployees) {
            if(!firstCriteriaEmployees.contains(employee)) {
                firstCriteriaEmployees.add(employee);
            }
        }
        return firstCriteriaEmployees;
    }
}
```

Теперь, когда все реализации Criteria созданы, давайте составим список сотрудников, которые будут действовать как список, извлеченный из базы данных, а затем выполним несколько критериев:

```java
public class Main {  
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();

        //adding employees to the list
        employeeList.add(new Employee("David", "Male", "Senior"));
        employeeList.add(new Employee("Scott", "Male", "Senior"));
        employeeList.add(new Employee("Rhett", "Male", "Junior"));
        employeeList.add(new Employee("Andrew", "Male", "Junior"));
        employeeList.add(new Employee("Susan", "Female", "Senior"));
        employeeList.add(new Employee("Rebecca", "Female", "Junior"));
        employeeList.add(new Employee("Mary", "Female", "Junior"));
        employeeList.add(new Employee("Juliette", "Female", "Senior"));
        employeeList.add(new Employee("Jessica", "Female", "Junior"));
        employeeList.add(new Employee("Mike", "Male", "Junior"));
        employeeList.add(new Employee("Chris", "Male", "Junior"));

        //initialization of the different criteria classes
        Criteria maleEmployees = new CriteriaMale();
        Criteria femaleEmployees = new CriteriaFemale();
        Criteria seniorEmployees = new CriteriaSenior();
        Criteria juniorEmployees = new CriteriaJunior();
        //AndCriteria and OrCriteria accept two Criteria as their constructor    
        arguments and return filtered lists
        Criteria seniorFemale = new AndCriteria(seniorEmployees, femaleEmployees);
        Criteria juniorOrMale = new OrCriteria(juniorEmployees, maleEmployees);

        System.out.println("Male employees: ");
        printEmployeeInfo(maleEmployees.criteria(employeeList));

        System.out.println("\nFemale employees: ");
        printEmployeeInfo(femaleEmployees.criteria(employeeList));

        System.out.println("\nSenior female employees: ");
        printEmployeeInfo(seniorFemale.criteria(employeeList));

        System.out.println("\nJunior or male employees: ");
        printEmployeeInfo(juniorOrMale.criteria(employeeList));
    }


    //simple method to print out employee info
    public static void printEmployeeInfo(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            System.out.println("Employee info: | Name: " 
                    + employee.getName() + ", Gender: " 
                    + employee.getGender() + ", Position: " 
                    + employee.getPosition() + " |");
        }
    }
}
```

Запуск этого кода даст:

```java
Male employees:  
Employee info: | Name: David, Gender: Male, Position: Senior |  
Employee info: | Name: Scott, Gender: Male, Position: Senior |  
Employee info: | Name: Rhett, Gender: Male, Position: Junior |  
Employee info: | Name: Andrew, Gender: Male, Position: Junior |  
Employee info: | Name: Mike, Gender: Male, Position: Junior |  
Employee info: | Name: Chris, Gender: Male, Position: Junior |

Female employees:  
Employee info: | Name: Susan, Gender: Female, Position: Senior |  
Employee info: | Name: Rebecca, Gender: Female, Position: Junior |  
Employee info: | Name: Mary, Gender: Female, Position: Junior |  
Employee info: | Name: Juliette, Gender: Female, Position: Senior |  
Employee info: | Name: Jessica, Gender: Female, Position: Junior |

Senior female employees:  
Employee info: | Name: Susan, Gender: Female, Position: Senior |  
Employee info: | Name: Juliette, Gender: Female, Position: Senior |

Junior or male employees:  
Employee info: | Name: Rhett, Gender: Male, Position: Junior |  
Employee info: | Name: Andrew, Gender: Male, Position: Junior |  
Employee info: | Name: Rebecca, Gender: Female, Position: Junior |  
Employee info: | Name: Mary, Gender: Female, Position: Junior |  
Employee info: | Name: Jessica, Gender: Female, Position: Junior |  
Employee info: | Name: Mike, Gender: Male, Position: Junior |  
Employee info: | Name: Chris, Gender: Male, Position: Junior |  
Employee info: | Name: David, Gender: Male, Position: Senior |  
Employee info: | Name: Scott, Gender: Male, Position: Senior |  
```