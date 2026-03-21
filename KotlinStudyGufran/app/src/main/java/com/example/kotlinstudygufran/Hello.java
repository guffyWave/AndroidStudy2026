import java.util.*;

//Sort this array (ascending order)
 class Main {
    public static void main(String[] args) {

        Human human = new Human();

        Machine machine=new Machine();

        MasterObservable masterObservable = new MasterObservable();
        masterObservable.subscribe(human);
        masterObservable.subscribe(machine);



    }

}


class Human implements Observer {
    String name;

    public Human() {
        System.out.println("Initialising Human ");
    }

    public void doWork() {

    }

    @Override
    public void listen(String data) {
        System.out.println("Human Listening to data "+ data);
    }
}


class Machine implements Observer {
    String name;

    public Machine() {
        System.out.println("Initialising Machine ");
    }

    @Override
    public void listen(String data) {
        System.out.println("Machine Listening to data "+ data);
    }
}

interface Observable {
    public void subscribe(Observer observer);

    public void unSubscribe(Observer observer);

    public void announce();
}

interface Observer {
    public void listen(String data);
}

//
class MasterObservable implements Observable {

    private List<Observer> observerList = new ArrayList<>();


    public MasterObservable() {
        observerList = new ArrayList<Observer>();
    }

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
    }

    public void announce() {
        System.out.println("Master Announcing  ! ");
        for (Observer observer :
                observerList) {
            observer.listen("Hello World");
        }
    }

}

