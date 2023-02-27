import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
public static void main(String[] args) {

}

public static final String AUSTIN_POWERS = "Austin Powers";
public static final String WEAPONS = "weapons";
public static final String BANNED_SUBSTANCE = "banned substance";

public static interface Sendable {
    String getFrom();

    String getTo();
}

public static abstract class AbstractSendable implements Sendable {

    protected final String from;
    protected final String to;

    public AbstractSendable(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSendable that = (AbstractSendable) o;

        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;

        return true;
    }

}

public static class MailMessage extends AbstractSendable {

    private final String message;

    public MailMessage(String from, String to, String message) {
        super(from, to);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailMessage that = (MailMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

}

public static class MailPackage extends AbstractSendable {
    private final Package content;

    public MailPackage(String from, String to, Package content) {
        super(from, to);
        this.content = content;
    }

    public Package getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailPackage that = (MailPackage) o;

        if (!content.equals(that.content)) return false;

        return true;
    }

}

public static class Package {
    private final String content;
    private final int price;

    public Package(String content, int price) {
        this.content = content;
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (price != aPackage.price) return false;
        if (!content.equals(aPackage.content)) return false;

        return true;
    }
}

public static interface MailService {
    Sendable processMail(Sendable mail);
}

/*
Класс, в котором скрыта логика настоящей почты
*/
public static class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}

public static class UntrustworthyMailWorker implements MailService{
    UntrustworthyMailWorker(MailService[] MailService) {
    }

    public Object UntrustworthyMailWorker(MailService[] mailService){
        Object o=new Object();
        for (MailService i:mailService) {
            o.proccessMail;
        }
        return
    }

    RealMailService rms = new RealMailService();


    private RealMailService getRealMailService() {

        return
    }


    @Override
    public Sendable processMail(Sendable mail) {
        return null;
    }
}

public static class Spy extends MailMessage {

    Spy(String from, String to, String message) {
        super(from, to, message);
    }


    public void Spy() {
        MailMessage mm = new MailMessage(getFrom(), getTo(), getMessage());
        if (mm.getFrom().contains(AUSTIN_POWERS) || mm.getTo().contains(AUSTIN_POWERS)) {
            Logger logger = Logger.getLogger("log");
            logger.log(Level.WARNING, "Detected target mail correspondence: from" + getFrom() + "to" + getTo() + getMessage());
        } else {
            Logger logger1 = Logger.getLogger("log1");
            logger1.log(Level.INFO, "Usual correspondence: from" + getFrom() + "to" + getTo());
        }
    }
}

public static class Thief extends Package {

    int cost;
    int summ = 0;

    Thief(String content, int price) {
        super(content, price);

    }

    public Package Thief() {
        Thief tf = new Thief(getContent(), getPrice());
        if (getPrice() > getCost()) {
            Thief tf1 = new Thief(getContent1(), getPrice1());
            summ += getPrice();
            return tf1;
        } else return tf;

    }

    private int getPrice1() {
        return 0;
    }

    private String getContent1() {
        return "stones instead of " + getContent();
    }


    public int getStolenValue() {
        return summ;
    }

    public int getCost() {
        return cost;
    }
}


public static class Inspector extends Package {


    Inspector(String content, int price) {
        super(content, price);
    }

    public void Inspector() throws Exception {
        Package aPackage = new Package(getContent(), getPrice());
        try {
            if (aPackage.getContent().equals("weapons") || aPackage.getContent().equals("banned substance")) {
            }
        } catch (Exception e) {
            throw new IllegalPackageException();
        }
        try {
            if (aPackage.getContent().equals("stones")) {
            }

        } catch (Exception e) {
            throw new StolenPackageException();
        }
        try {
            if (aPackage.getPrice() == 0) {
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    static class IllegalPackageException extends Exception {
    }

    static class StolenPackageException extends Exception {

    }


}
}
