package DLFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DLFrame extends Frame implements ActionListener, Mediator {
    private ColleagueCheckBox checkJPG;
    private ColleagueCheckBox checkPNG;
    private ColleagueCheckBox checkDigitOne;
    private ColleagueCheckBox checkDigitTwo;
    private ColleagueCheckBox checkDigitThree;
    private ColleagueTextField textHost;
    private ColleagueTextField textNewDir;
    private ColleagueTextField textMaxPage;
    private ColleagueTextField textPrefix;
    private ColleagueButton buttonDL;

    public DLFrame(String title) {
        super(title);
        setSize(100, 1000);
        setBackground(Color.lightGray);

        setLayout(new GridLayout(8, 2));
//        setLayout(new FlowLayout());

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
        add(checkDigitOne);
        add(checkDigitTwo);
        add(checkDigitThree);
        add(new Label(""));
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
        checkDigitOne = new ColleagueCheckBox("1ケタ", groupZeroPad, true);
        checkDigitTwo = new ColleagueCheckBox("2ケタ", groupZeroPad, false);
        checkDigitThree = new ColleagueCheckBox("3ケタ", groupZeroPad, false);
        textHost = new ColleagueTextField("http://", 30);
        textNewDir = new ColleagueTextField("src", 20);
        textMaxPage = new ColleagueTextField("100", 10);
        textPrefix = new ColleagueTextField("", 20);
        buttonDL = new ColleagueButton("ダウンロード");

        // SET MEDIATOR
        checkJPG.setMediator(this);

        checkPNG.setMediator(this);
        checkDigitOne.setMediator(this);
        checkDigitTwo.setMediator(this);
        checkDigitThree.setMediator(this);
        textHost.setMediator(this);
        textNewDir.setMediator(this);
        textMaxPage.setMediator(this);
        textPrefix.setMediator(this);

        buttonDL.setMediator(this);

        // SET LISTENER
        checkJPG.addItemListener(checkJPG);
        checkPNG.addItemListener(checkPNG);
        checkDigitOne.addItemListener(checkDigitOne);
        checkDigitTwo.addItemListener(checkDigitTwo);
        checkDigitThree.addItemListener(checkDigitThree);
        textHost.addTextListener(textHost);
        textNewDir.addTextListener(textNewDir);
        textMaxPage.addTextListener(textMaxPage);
        textPrefix.addTextListener(textPrefix);
    }

    @Override
    public void colleagueChanged() {
        attrChanged();
    }

    private void attrChanged() {
        // Hostが空欄の時は ダウンロードボタンを不活性
        if (textHost.getText().length() > 0) {
            buttonDL.setColleagueEnabled(true);
        } else {
            buttonDL.setColleagueEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }
}
