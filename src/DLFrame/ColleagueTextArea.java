package DLFrame;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueTextArea extends TextArea implements TextListener, Colleague {
    private Mediator mediator;

    public ColleagueTextArea(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }

    public void textValueChanged(TextEvent e) {
        mediator.colleagueChanged();
    }
}
