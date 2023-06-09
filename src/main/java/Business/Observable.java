package Business;

import Presentation.Observer;

public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObservers();

}
