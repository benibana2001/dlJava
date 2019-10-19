package DLFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

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
    private ColleagueTextArea textAreaFQN;

    private DLFrameGateway dlFrameGateway;

    public DLFrame(String title) {
        super(title);
        //
        dlFrameGateway = new DLFrameGateway();
        //
        setSize(100, 1000);
        setBackground(Color.lightGray);

        setLayout(new GridLayout(9, 2));
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
        add(textAreaFQN);
        //
        add(buttonDL);
        // ==============================================
        colleagueChanged();
        //
        pack();
        setVisible(true);
    }

    private final String JPG = "JPG";
    private final String PNG = "PNG";
    private final String digitOne = "1ケタ";
    private final String digitTwo = "2ケタ";
    private final String digitThree = "3ケタ";

    public void createColleagues() {
        CheckboxGroup groupExtension = new CheckboxGroup();
        checkJPG = new ColleagueCheckBox(JPG, groupExtension, true);
        checkPNG = new ColleagueCheckBox(PNG, groupExtension, false);
        CheckboxGroup groupZeroPad = new CheckboxGroup();
        checkDigitOne = new ColleagueCheckBox(digitOne, groupZeroPad, true);
        checkDigitTwo = new ColleagueCheckBox(digitTwo, groupZeroPad, false);
        checkDigitThree = new ColleagueCheckBox(digitThree, groupZeroPad, false);
        textHost = new ColleagueTextField("http://", 30);
        textNewDir = new ColleagueTextField("src", 20);
        textMaxPage = new ColleagueTextField("100", 10);
        textPrefix = new ColleagueTextField("", 20);
        textAreaFQN = new ColleagueTextArea(1, 20);
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
        buttonDL.addActionListener(this);
    }

    @Override
    public void colleagueChanged() {
        attrChanged();
    }

    public void colleagueCheckBoxChanged(ItemEvent e) {
        System.out.println(e.getItem().toString());
        switch (e.getItem().toString()){
            case PNG:
                dlFrameGateway.setExt(".png");
                break;
            case JPG:
                dlFrameGateway.setExt(".jpg");
                break;
            case digitOne:
                dlFrameGateway.setZeroPad(0);
                break;
            case digitTwo:
                dlFrameGateway.setZeroPad(1);
                break;
            case digitThree:
                dlFrameGateway.setZeroPad(2);
                break;
            default:
                break;
        }
    }

    private void attrChanged() {
        // Hostが空欄の時は ダウンロードボタンを不活性
        if (textHost.getText().length() > 0) {
            textHost.setBackground(Color.white);
            buttonDL.setColleagueEnabled(true);
            this.dlFrameGateway.setText(textHost.getText());
        } else {
            textHost.setBackground(Color.red);
            buttonDL.setColleagueEnabled(false);
            this.dlFrameGateway.setText("");
        }
        //
        if (textNewDir.getText().length() > 0) {
            textNewDir.setBackground(Color.white);
            this.dlFrameGateway.setNewDir(textNewDir.getText());
        } else {
            textNewDir.setBackground(Color.red);
            buttonDL.setColleagueEnabled(false);
            this.dlFrameGateway.setText("");
        }
        //
        if (textMaxPage.getText().length() > 0) {
            textMaxPage.setBackground(Color.white);
            this.dlFrameGateway.setMaxPage(textMaxPage.getX());
        } else {
            textMaxPage.setBackground(Color.red);
            buttonDL.setColleagueEnabled(false);
            this.dlFrameGateway.setMaxPage(0);
        }
        //
        if (textPrefix.getText().length() > 0) {
            this.dlFrameGateway.setPrefix(textPrefix.getText());
        } else {
            this.dlFrameGateway.setPrefix("");
        }

        // FQNを描画
        textAreaFQN.replaceRange(dlFrameGateway.getFQN(), 0, textAreaFQN.getText().length());
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        if (e.getSource() == buttonDL) {
            DLFrameGateway.hello();
        }
    }
}
