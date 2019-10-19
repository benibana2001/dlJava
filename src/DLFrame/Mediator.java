package DLFrame;

import java.awt.event.ItemEvent;

public interface Mediator {
    public abstract void createColleagues();
    public abstract void colleagueChanged();
    public abstract void colleagueCheckBoxChanged(ItemEvent e);
}
