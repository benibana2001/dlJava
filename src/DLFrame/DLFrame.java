package DLFrame;

import java.awt.*;
import java.awt.event.ActionListener;

public class DLFrame extends Frame implements ActionListener, Mediator {
    private ColleagueCheckBox checkJPG;
    private ColleagueCheckBox checkPNG;
    private ColleagueCheckBox digitOne;
    private ColleagueCheckBox digitTwo;
    private ColleagueCheckBox digitThree;
    private ColleagueTextField textHost;
    private ColleagueTextField textNewDir;
    private ColleagueTextField textMaxPage;
    private ColleagueTextField textPrefix;
    private ColleagueButton buttonDL;

    public DLFrame(String title) {
        super(title);
        setBackground(Color.lightGray);

        setLayout(new GridLayout(7, 3));

        createColleagues();
        // ==============================================
        add(new Label("HOST"));
        add(textHost);
        //
        add(new Label("保存ディレクトリ名"));
        add(textNewDir);
        //
        add(checkJPG);
        add(checkPNG);
        //
        add(digitOne);
        add(digitTwo);
        add(digitThree);
        //
        add(new Label("最大ベージ数"));
        add(textMaxPage);
        //
        add(new Label("接頭辞"));
        add(textPrefix);
        //
        add(buttonDL);
        // ==============================================
        colleagueChanged();

        pack();
        setVisible(true);
    }

    public void createColleagues() {
        CheckboxGroup groupExtension = new CheckboxGroup();
        checkJPG = new ColleagueCheckBox("JPG", groupExtension, true);
        checkPNG = new ColleagueCheckBox("PNG", groupExtension, false);
        CheckboxGroup groupZeroPad = new CheckboxGroup();
        digitOne = new ColleagueCheckBox("1ケタ", groupZeroPad, true);
        digitTwo = new ColleagueCheckBox("2ケタ", groupZeroPad, false);
        digitThree = new ColleagueCheckBox("3ケタ", groupZeroPad, false);
        textHost = new ColleagueTextField("http://", 70);
        textNewDir = new ColleagueTextField("src", 20);
        textMaxPage = new ColleagueTextField("100", 10);
        textPrefix = new ColleagueTextField("", 20);
        buttonDL = new ColleagueButton("ダウンロード");

        // SET MEDIATOR
        checkJPG.setMediator(this);

        checkPNG.setMediator(this);
        digitOne.setMediator(this);
        digitTwo.setMediator(this);
        digitThree.setMediator(this);
        textHost.setMediator(this);
        textNewDir.setMediator(this);
        textMaxPage.setMediator(this);
        textPrefix.setMediator(this);

        buttonDL.setMediator(this);
    }


}
