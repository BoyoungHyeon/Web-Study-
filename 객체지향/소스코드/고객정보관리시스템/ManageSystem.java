package 고객관리구축;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
        << 개정 이력 ( Modification Infomation ) >>
        수정일             수정자             작업 내용/ 수정내용
       --------         -----------          ---------------------
       2021.10.07          현보영              레이아웃 설계, 기능 구현,

 */
public class ManageSystem extends JFrame {      // JFrame 클래스는 최상위 컨테이너 역할의 클래스이다.
    public static final String String = null;

    // 내부 클래스 객체 생성 => 내부 클래스 각 참조 변수
    MenuMain menuMain = new MenuMain();
    West west = new West();
    Buttons buttons = new Buttons();
    ShowTable showTable = new ShowTable();

    // 외부 클래스 => 생성자
    public ManageSystem() {
        OUTTER : while(true) {
            ImageIcon icon = new ImageIcon("src/Images/고객정보관리_이미지.jpg");
            JOptionPane.showMessageDialog(null, null, "고객정보 관리 시스템", JOptionPane.NO_OPTION, icon);

            String password = JOptionPane.showInputDialog("고객 관리 시스템" + "\n" + "패스워드 입력");
            String passwd = "king1234";

            if (password == null) {
                break OUTTER;
            } else if (password.equals(passwd)) { //  패스워드 인증 처리 성공 시 => 실행 화면을 띄우기
                setTitle("고객정보 관리 시스템");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 추가
                add(menuMain.bar, BorderLayout.NORTH);



                setSize(1200, 800);
                setLocation(500, 50);
                setVisible(true);

                break OUTTER;
            } else {
                JOptionPane.showConfirmDialog(null, "패스워드가 맞지 않습니다." + "\n" + "'확인'버튼을 누르세요", "에러 메시지", JOptionPane.ERROR_MESSAGE);
                continue OUTTER;
            }
        }
    }
    // 메뉴 만들기 => 내부 클래스(inner class )
    class MenuMain extends JPanel implements ActionListener, ItemListener{

        JMenuBar            bar;
        JMenu               file, sort, help;
        JMenuItem           fnew, fopen, fexit, proinfo, fsave;
        JCheckBoxMenuItem   sno, sname, schul, sjob;

        FileDialog roadOpen, saveOpen;
        String fileDir, fileName, savefileName, readfileName;
        ButtonGroup gr = new ButtonGroup();


        // 생성자
        public MenuMain() {
            bar = new JMenuBar();
            file = new JMenu("파일");
            sort = new JMenu("정렬");
            help = new JMenu("도움말");

            fopen = new JMenuItem("열기");
            fsave = new JMenuItem("저장");
            fexit = new JMenuItem("닫기");

            sno = new JCheckBoxMenuItem("번호");
            sname = new JCheckBoxMenuItem("이름");
            schul = new JCheckBoxMenuItem("출신지역");
            sjob = new JCheckBoxMenuItem("직업");

            proinfo = new JMenuItem("프로그램 정보");

            // 객체들 컨테이너에 붙이기
            file.add(fopen);
            file.add(fsave);
            file.addSeparator();
            file.add(fexit);

            gr.add(sno);
            gr.add(sname);
            gr.add(schul);
            gr.add(sjob);

            sort.add(sno);
            sort.add(sname);
            sort.add(schul);
            sort.add(sjob);

            help.add(proinfo);

            bar.add(file);
            bar.add(sort);
            bar.add(help);

            // 파일 메뉴 이벤트 연결
            fopen.addActionListener(this);
            fsave.addActionListener(this);
            fexit.addActionListener(this);

            // 정렬 메뉴 이벤트 연결
            sno.addItemListener(this);
            sname.addItemListener(this);
            schul.addItemListener(this);
            sjob.addItemListener(this);


        }

        @Override
        public void itemStateChanged(ItemEvent e){

        }
        @Override
        public void actionPerformed(ActionEvent e){

        }

    } // end MenyMain 클래스 =======================================================================================

    // 입력, 신상 정보 즉 West => 내부 클래스
    class West extends JPanel {

        // 생성자
        public West() {

        }
        class Input extends JPanel {     // 입력정보 담당 클래스

            // 생성자
            public Input(){

            }
        }
        class Output extends JPanel implements ActionListener {  // 신상 정보 담당 클래스

            // 생성자
            public Output(){

            }

            @Override
            public void actionPerformed(ActionEvent e){

            }
        }
    } // end West 클래스 ==========================================================================================

    // 버튼들 만들기 => 내부 클래스
    class Buttons extends JPanel implements ActionListener{

        // 생성자
        public Buttons(){

        }

        @Override
        public void actionPerformed(ActionEvent e){

        }
    } // end Buttons 클래스 =====================================================================================

    // 센터쪽 => Jtable 만들기 => 내부 클래스
    class ShowTable extends MouseAdapter {

        // 생성자
        public ShowTable(){

        }

    }

    public static void main(String[] args) {
        ManageSystem ms = new ManageSystem();
    }
}
