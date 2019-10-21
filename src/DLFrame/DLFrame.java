package DLFrame;

import DLException.FailedMkDir;
import Util.Util;

import java.awt.*;
import java.awt.event.*;

public class DLFrame extends Frame implements ActionListener, Mediator {
    private ColleagueCheckBox checkJPG;
    private ColleagueCheckBox checkPNG;
    private ColleagueCheckBox checkGIF;
    private ColleagueCheckBox checkDigitOne;
    private ColleagueCheckBox checkDigitTwo;
    private ColleagueCheckBox checkDigitThree;
    private ColleagueTextField textHost;
    private ColleagueTextField textNewDir;
    private ColleagueTextField textMaxPage;
    private ColleagueTextField textStartPage;
    private ColleagueTextField textPrefix;
    private ColleagueButton buttonDL;
    private ColleagueTextArea textAreaFQN;

    private DLFrameGateway dlFrameGateway;

    private Dialog popWhileDL;
    private Dialog popDuplicateDir;

    public DLFrame(String title) {
        super(title);
        //
        dlFrameGateway = new DLFrameGateway();
        //
        setSize(100, 1000);
        setBackground(Color.lightGray);
        //
        addWindowListener(new WAdapter());
        setLayout(new GridLayout(23, 1));
//        setLayout(new FlowLayout());

        createColleagues();
        // ==============================================
        add(new Label("HOST"));
        add(textHost);
        //
        add(new Label("保存ディレクトリ名"));
        add(textNewDir);
        //
        add(new Label("拡張子"));
        add(checkJPG);
        add(checkPNG);
        add(checkGIF);
        //
        add(new Label("ゼロ埋め"));
        add(checkDigitOne);
        add(checkDigitTwo);
        add(checkDigitThree);
        //
        add(new Label("開始ページ番号"));
        add(textStartPage);
        //
        add(new Label("最大ベージ数（リクエスト数）"));
        add(textMaxPage);
        //
        add(new Label("ファイル名の接頭辞"));
        add(textPrefix);
        //
        add(new Label("サンプル"));
        add(textAreaFQN);
        //
        add(new Label(""));
        add(buttonDL);
        // ==============================================
        colleagueChanged();
        //
        pack();
        setVisible(true);
    }

    private final String JPG = "JPG";
    private final String PNG = "PNG";
    private final String GIF = "GIF";
    private final String digitOne = "1ケタ";
    private final String digitTwo = "2ケタ";
    private final String digitThree = "3ケタ";

    public void createColleagues() {
        CheckboxGroup groupExtension = new CheckboxGroup();
        checkJPG = new ColleagueCheckBox(JPG, groupExtension, true);
        checkPNG = new ColleagueCheckBox(PNG, groupExtension, false);
        checkGIF = new ColleagueCheckBox(GIF, groupExtension, false);
        CheckboxGroup groupZeroPad = new CheckboxGroup();
        checkDigitOne = new ColleagueCheckBox(digitOne, groupZeroPad, true);
        checkDigitTwo = new ColleagueCheckBox(digitTwo, groupZeroPad, false);
        checkDigitThree = new ColleagueCheckBox(digitThree, groupZeroPad, false);
        textHost = new ColleagueTextField(" http://tk2-255-37178.vs.sakura.ne.jp/portfolio/poll/img/", 30);
        textNewDir = new ColleagueTextField("img", 20);
        textStartPage = new ColleagueTextField("1", 4);
        textMaxPage = new ColleagueTextField("100", 4);
        textPrefix = new ColleagueTextField("photo_", 20);
        textAreaFQN = new ColleagueTextArea(1, 20);
        buttonDL = new ColleagueButton("ダウンロード");

        // SET MEDIATOR
        checkJPG.setMediator(this);
        checkPNG.setMediator(this);
        checkGIF.setMediator(this);
        checkDigitOne.setMediator(this);
        checkDigitTwo.setMediator(this);
        checkDigitThree.setMediator(this);
        textHost.setMediator(this);
        textNewDir.setMediator(this);
        textMaxPage.setMediator(this);
        textStartPage.setMediator(this);
        textPrefix.setMediator(this);
        buttonDL.setMediator(this);

        // SET LISTENER
        checkJPG.addItemListener(checkJPG);
        checkPNG.addItemListener(checkPNG);
        checkGIF.addItemListener(checkGIF);
        checkDigitOne.addItemListener(checkDigitOne);
        checkDigitTwo.addItemListener(checkDigitTwo);
        checkDigitThree.addItemListener(checkDigitThree);
        textHost.addTextListener(textHost);
        textNewDir.addTextListener(textNewDir);
        textStartPage.addTextListener(textStartPage);
        textMaxPage.addTextListener(textMaxPage);
        textPrefix.addTextListener(textPrefix);
        buttonDL.addActionListener(this);

        // SET POPUP
        setPopWhileDL("ダウンロードを実行しています。");
        setPopDuplicateDir("ディレクトリの作成に失敗しました。名前を確認してください。");
        // POPUP INITIALIZE
        visiblePop(this.popWhileDL, true);
        visiblePop(this.popWhileDL, false);
        visiblePop(this.popDuplicateDir, true);
        visiblePop(this.popDuplicateDir, false);
    }

    @Override
    public void colleagueChanged() {
        attrChanged();
    }

    public void colleagueCheckBoxChanged(ItemEvent e) {
        switch (e.getItem().toString()) {
            case PNG:
                dlFrameGateway.setExt(".png");
                break;
            case JPG:
                dlFrameGateway.setExt(".jpg");
                break;
            case GIF:
                dlFrameGateway.setExt(".gif");
                break;
            case digitOne:
                dlFrameGateway.setZeroPad(1);
                break;
            case digitTwo:
                dlFrameGateway.setZeroPad(2);
                break;
            case digitThree:
                dlFrameGateway.setZeroPad(3);
                break;
            default:
                break;
        }
        writeFQN();
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
        // todo: 初回アクセス時、isValidがfalseでelseブロックに入る
        if (textStartPage.getText().length() > 0) {
            textStartPage.setBackground(Color.white);
            this.dlFrameGateway.setStartPage(Integer.parseInt(textStartPage.getText()));
        } else {
            textMaxPage.setBackground(Color.red);
//            buttonDL.setColleagueEnabled(false);
            this.dlFrameGateway.setStartPage(1);
        }
        //
        if (Integer.parseInt(textMaxPage.getText()) > 0) {
            textMaxPage.setBackground(Color.white);
            this.dlFrameGateway.setMaxPage(Integer.parseInt(textMaxPage.getText()));
        } else {
            textMaxPage.setBackground(Color.red);
            buttonDL.setColleagueEnabled(false);
            this.dlFrameGateway.setMaxPage(1);
        }
        //
        if (textPrefix.getText().length() > 0) {
            this.dlFrameGateway.setPrefix(textPrefix.getText());
        } else {
            this.dlFrameGateway.setPrefix("");
        }
        //
        writeFQN();
    }

    // FQNを描画
    private void writeFQN() {
        textAreaFQN.replaceRange(dlFrameGateway.getHost() + dlFrameGateway.getPrefix() + Util.paddingZero(dlFrameGateway.getZeroPad(), dlFrameGateway.getStartPage()) + dlFrameGateway.getExt(), 0, textAreaFQN.getText().length());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDL) {
            System.out.println("リクエスト開始");
            DLFrameGateway.hello();
            try {
                // 実行中はボタンを非活性
                buttonDL.setColleagueEnabled(false);
                textHost.setBackground(Color.darkGray);
                System.out.println("ポップアップを表示。");
                visiblePop(this.popWhileDL, true);
                this.dlFrameGateway.download();
            } catch (FailedMkDir f) {
                f.printStackTrace();
                System.out.println("ディレクトリエラー");
                // ディレクトリの作成に失敗した場合はポップアップを表示する
                buttonDL.setColleagueEnabled(true);
                textHost.setBackground(Color.white);
                visiblePop(this.popDuplicateDir, true);
                visiblePop(this.popWhileDL, false);
            } catch (Exception ex) {
                ex.printStackTrace();
                // 実行完了後はボタンを活性化
                buttonDL.setColleagueEnabled(true);
                textHost.setBackground(Color.white);
                System.out.println("ポップアップを非表示。");
                visiblePop(this.popWhileDL, false);
            }
        }

        if (e.getActionCommand().equals("OK")) {
            System.out.println("OKが押下されました。");
            this.popDuplicateDir.dispose();
        }
    }

    class WAdapter extends WindowAdapter {
        public void windowOpened(WindowEvent e) {
            System.out.println("System Start!");
        }

        public void windowClosing(WindowEvent e) {
            System.out.println("Finish System.");
            System.exit(0);
        }
    }

    private void setPopWhileDL(String message) {
        this.popWhileDL = new Dialog(this, "ダウンロード");
        this.popWhileDL.setLayout(new FlowLayout());
        this.popWhileDL.setResizable(false);
        this.popWhileDL.add(new Label(message));
        this.popWhileDL.setBackground(Color.yellow);
        this.popWhileDL.setSize(400, 100);
        this.popWhileDL.setVisible(false);
    }

    private void setPopDuplicateDir(String message) {
        this.popDuplicateDir = new Dialog(this, "エラー");
        this.popDuplicateDir.setLayout(new FlowLayout());
        this.popDuplicateDir.setResizable(false);
        this.popDuplicateDir.add(new Label(message));
        this.popDuplicateDir.setBackground(Color.yellow);
        this.popDuplicateDir.setSize(400, 100);
        Button b = (Button) this.popDuplicateDir.add(new Button("OK"));
        b.addActionListener(this);
        this.popDuplicateDir.setVisible(false);
    }

    private void visiblePop(Dialog d, Boolean isVisible) {
        d.setVisible(isVisible);
    }
}
