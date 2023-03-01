import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
public static void main(String[] args) {

    Logger logger = Logger.getLogger(main.class.getName());
    Inspector inspector = new Inspector();
    Spy spy = new Spy(logger);
    Thief thief = new Thief(10000);
    UntrustworthyMailWorker worker = new UntrustworthyMailWorker(new MailService[]{spy, thief, inspector});
    AbstractSendable[] correspondence = {
            new MailMessage("Oxxxymiron", "Гнойный", " Я здесь чисто по фану, поглумиться над слабым\n" +
                    "Ты же вылез из мамы под мой дисс на Бабана...."),
            new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                    "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
            new MailMessage("Жириновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
            new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
            new MailPackage("берег", "море", new Package("ВВЖ", 32)),
            new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
            new MailPackage(AUSTIN_POWERS, "NASA", new Package("ракетный двигатель ", 2500000)),
            new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones ", 1000)),
            new MailPackage("Китай", "КНДР", new Package("banned substance ", 10000)),
            new MailPackage(AUSTIN_POWERS, "Жопа запрещенная группировка", new Package("tiny bomb", 9000)),
            new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
    };

    for (AbstractSendable p : correspondence) {
        try {
            print("До:  ", p);
            Sendable sendable = worker.processMail(p);
            print("После:  ", sendable);
        } catch (Exception e) {
            logger.log(Level.WARNING, "из: " + p.getFrom() + " куда: " + p.getTo() + " Содержимое: "
                    + (p instanceof MailMessage ? ((MailMessage) p).getMessage() : ((MailPackage) p).getContent().getContent()
                    + " Цена=" + ((MailPackage) p).getContent().getPrice()) + " Exceptions: " + e);
        }
    }
}

public static void print(String prefix, Sendable p) {
    System.out.println(prefix + "из: " + p.getFrom() + " куда: " + p.getTo() + " Содержимое: "
            + (p instanceof MailMessage ? ((MailMessage) p).getMessage() : ((MailPackage) p).getContent().getContent()
            + " Цена=" + ((MailPackage) p).getContent().getPrice()));
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
    Sendable processMail(Sendable mail) throws Exception;
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

public static class UntrustworthyMailWorker implements MailService {
    private RealMailService realMailService;
    private MailService[] mailServices;

    public UntrustworthyMailWorker(MailService[] mailServices) {
        this.mailServices = mailServices;
        for (MailService ms : this.mailServices) {
            if (ms instanceof RealMailService)
                this.realMailService = (RealMailService) ms;
        }
        if (this.realMailService == null) {
            this.realMailService = new RealMailService();
        }
    }

    @Override
    public Sendable processMail(Sendable mail) throws Exception {
        Sendable m = mail;
        for (MailService ms : this.mailServices) {
            if (ms instanceof RealMailService)
                continue;
            m = ms.processMail(m);
        }
        if (getRealMailService() != null) {
            m = this.realMailService.processMail(m);
        }
        return m;
    }


    public RealMailService getRealMailService() {
        return realMailService;
    }
}

public static class Spy implements MailService {
    private final static Logger LOGGER = Logger.getLogger(Spy.class.getName());
    private final Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            MailMessage mm = (MailMessage) mail;
            if (mm.getFrom().contains(AUSTIN_POWERS) || mm.getTo().contains(AUSTIN_POWERS)) {
                Logger logger = Logger.getLogger("log");
                logger.log(Level.WARNING, "Detected target mail correspondence: from" + mm.getFrom() + "to" + mm.getTo() + mm.getMessage());
            } else {
                Logger logger1 = Logger.getLogger("log1");
                logger1.log(Level.INFO, "Usual correspondence: from" + mm.getFrom() + "to" + mm.getTo());
            }
        }
        return mail;
    }
}

public static class Thief implements MailService {

    private int cost;
    private int summ = 0;

    public Thief(int cost) {
        this.cost = cost;
    }

    public int getStolenValue() {
        return summ;
    }


    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            MailPackage pg = (MailPackage) mail;
            if (pg.getContent().getPrice() >= cost) {
                this.summ += pg.getContent().getPrice();
                return new MailPackage(mail.getFrom(), mail.getTo(), new Package("stones instead of " + pg.getContent().getContent(), 0));

            }
        }
        return mail;

    }


}

public static class Inspector implements MailService {


    @Override
    public Sendable processMail(Sendable mail) throws RuntimeException {
        if (mail instanceof Package) {
            MailPackage pg = (MailPackage) mail;

            if (pg.getContent().equals(WEAPONS) || pg.getContent().equals(BANNED_SUBSTANCE))
                throw new IllegalPackageException();
            if (pg.getContent().equals("stones"))
                throw new StolenPackageException();
            if (pg.getContent().getPrice() == 0)
                System.out.println("AAAAAAAAAAAA");
                throw new RuntimeException("посылка обворована");
        }
        return mail;
    }
}

public static class IllegalPackageException extends RuntimeException {
}

public static class StolenPackageException extends RuntimeException {
}


}
