package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Util {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WISHLIST_BOOKS = "wishlist_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";

    private static Util object;

    private SharedPreferences sharedPreferences;


    public static Util getObject(Context context) {
        if (null != object) {
            return object;
        } else {
            object = new Util(context);
            return object;
        }
    }

    public Util(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);

        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (null == getWishListBooks()){
            editor.putString(WISHLIST_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();

        }

        if (null == getFavouriteBooks()){
            editor.putString(FAVOURITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }
    }

    private void initData() {
        //TODO: add initial data

        ArrayList<Book> books =  new ArrayList<>();


        books.add(new Book(1,306,"Atomic Habits","James Clear","https://images-na.ssl-images-amazon.com/images/I/81wgcld4wxL.jpg","A supremely practical and useful book. James Clear distills the most fundamental information about habit formation, so you can accomplish more by focusing on less.",
                "Atomic Habits is the most comprehensive and practical guide on how to create good habits, break bad ones, and get 1 percent better every day. I do not believe you will find a more actionable book on the subject of habits and improvement." +
                "\n" + "If you’re having trouble changing your habits, the problem isn’t you. The problem is your system." +
                "\n" + "Bad habits repeat themselves not because you don’t want to change but because you have the wrong system for change. This is one of the core philosophies of Atomic Habits: You do not rise to the level of your goals. You fall to the level of your systems. In this book, you’ll get a proven plan that can take you to new heights." +
                "\n" + "James Clear, one of the world’s leading experts on habit formation, is known for his ability to distill complex topics into simple behaviors that can be easily applied to daily life and work. Here, he draws on the most proven ideas from biology, psychology, and neuroscience to create an easy-to-understand guide for making good habits inevitable and bad habits impossible." +
                "\n" + "Along the way, readers will be inspired and entertained with true stories about Olympic gold medalists, award-winning artists, business leaders, life-saving physicians, and star comedians who have used the science of small habits to master their craft and vault to the top of their field." +
                "\n" + "Atomic Habits will reshape the way you think about progress and success and give you the tools and strategies you need to transform your habits—whether you are a team looking to win a championship, an organization hoping to redefine an industry, or simply an individual who wishes to quit smoking, lose weight, reduce stress, and achieve success that lasts."));

        books.add(new Book(2,241,"Think and Grow Rich","Napoleon Hill","https://images-na.ssl-images-amazon.com/images/I/718wzK6mymL.jpg","Think And Grow Rich is a curation of the 13 most common habits of wealthy and successful people",
                "Think and Grow Rich is a book written by Napoleon Hill in 1937 and promoted as a personal development and self-improvement book. He claimed to be inspired by a suggestion from business magnate and later-philanthropist Andrew Carnegie." +
                "\n" + "Think and Grow Rich is based on Hill's earlier work The Law of Success, and is the result of more than twenty years of study of many individuals who had amassed personal fortunes." +
                "\n" + "Hill studied their habits and drew some 16 laws to be applied to achieve success. Think and Grow Rich condenses them, providing the reader with 14 principles in the form of a Philosophy of Achievement."));

        books.add(new Book(3,132,"The Theory Of Everything","Stephen Hawking","https://images-na.ssl-images-amazon.com/images/I/51oHUvYzbsL.jpg","The Theory of Everything by Stephen Hawking is a series of seven lectures. This book presents the most complex theories about black holes, expanding the universe, the origin of everything, the direction of time, and the big bang.",
                "In physicist Stephen Hawking's brilliant opus, A Brief History of Time, he presented us with a bold new look at our universe, how it began, and how our old views of physics and tired theories about the creation of the universe were no longer relevant." +
                "\n" + "In other words, Hawking gave us a new look at our world, our universe, and ourselves. Now, available for the first time in a deluxe full-color edition with never-before-seen photos and illustrations, Hawking presents an even more comprehensive look at our universe, its creation, and how we see ourselves within it. \" +\n" +
                "\n" + "Imagine sitting in a comfortable room listening to Hawking discuss his latest theories and place them in historical context with science's other great achievements--it would be like hearing Christopher Columbus deliver the news about the new world. Hawking presents a series of seven lectures in which he describes, more clearly than ever, the history of the universe as we know it. \" +\n" +
                "\n" + "He begins with the history of ideas about the universe, from Aristotle's idea that the Earth is round to Hubble's discovery two millennia later that our universe is growing. Using this history as a launching pad, Hawking takes us on a fascinating journey through the telescopic lens of modern physics to gain a new glimpse of the universe--the nature of black holes, the space-time continuum, and new information about the origin of the universe." +
                "\n" + "He uses this scientific basis to come up with a \\\"unified theory of everything\\\" that the author claims will be ..\\\".the ultimate triumph of human reason."));

        books.add(new Book(4,291,"How To Win Friends and Influence People","Dale Carnegie","https://m.media-amazon.com/images/I/41C9YOYkkOL._AC_SY780_.jpg","How to Win Friends and Influence People is a self-help book written by Dale Carnegie, published in 1936. Over 30 million copies have been sold worldwide, making it one of the best-selling books of all time. "," Dale Carnegie’s rock-solid, time-tested advice has carried countless people up the ladder of success in their business and personal lives. One of the most groundbreaking and timeless bestsellers of all time, How to Win Friends & Influence People will teach you:\n" +
                "\n" +
                "-Six ways to make people like you\n" +
                "\n" +
                "-Twelve ways to win people to your way of thinking\n" +
                "\n" +
                "-Nine ways to change people without arousing resentment\n" +
                "\n" +
                "And much more! Achieve your maximum potential—a must-read for the twenty-first century with more than 15 million copies sold! "));

        books.add(new Book(5,336,"Rich Dad Poor Dad","Robert Kiyosaki","https://kbimages1-a.akamaihd.net/ddf8d53d-7df5-4560-8fbd-43915f4f6a03/1200/1200/False/rich-dad-poor-dad-24.jpg","Rich Dad Poor Dad is a 1997 book written by Robert T. Kiyosaki and Sharon Lechter. It advocates the importance of financial literacy, financial independence and building wealth through investing in assets, real estate investing, starting and owning businesses, as well as increasing one's financial intelligence. ",
                "Rich Dad Poor Dad is Robert's story of growing up with two dads — his real father and the father of his best friend, his rich dad — and the ways in which both men shaped his thoughts about money and investing. The book explodes the myth that you need to earn a high income to be rich and explains the difference between working for money and having your money work for you." +
                "\n Rich Dad Poor Dad...\n" +
                "• Explodes the myth that you need to earn a high income to become rich\n" +
                "• Challenges the belief that your house is an asset\n" +
                "• Shows parents why they can't rely on the school system to teach their kids\n" +
                "about money\n" +
                "• Defines once and for all an asset and a liability\n" +
                "• Teaches you what to teach your kids about money for their future financial\n" +
                "success"));

        books.add(new Book(6,144,"The Richest Name in Babylon","George S. Clason","https://m.media-amazon.com/images/I/51pYZS7IWcL.jpg","The Richest Man in Babylon' is considered as the greatest of all inspirational works on the subject of thrift, financial planning, and personal wealth. Revealed inside are the secrets to acquiring money, keeping money, and making money earn more money.","The Richest Man in Babylon' is considered as the greatest of all inspirational works on the subject of thrift, financial planning, and personal wealth. Revealed inside are the secrets to acquiring money, keeping money, and making money earn more money.\n" +
                "\n" +
                "Providing financial wisdom through parables, 'The Richest Man in Babylon' was originally a set of pamphlets, written by the author and distributed by banks and insurance companies. These pamphlets were later bundled together, giving birth to a book. In this new rendering by Charles Conrad, the classic tale is retold in clear, simple language for today's readers. These fascinating and informative stories set you on a sure path to prosperity and its accompanying joys."));

        books.add(new Book(7,381,"The 7 Habits of Highly Effective People","Stephen Covey","https://images-na.ssl-images-amazon.com/images/I/817xk9KvJbL.jpg","Participants gain hands-on experience, applying timeless principles that yield greater productivity, improved communications, strengthened relationships, increased influence, and laser-like focus on critical priorities. The 7 Habits will help you: learn how to take initiative.","The 7 Habits of Highly Effective People, first published in 1989, is a business and self-help book written by Stephen R. Covey. Covey presents an approach to being effective in attaining goals by aligning oneself to what he calls \"true north\" principles based on a character ethic that he presents as universal and timeless.\n" +
                "\n" +
                "Covey defines effectiveness as the balance of obtaining desirable results with caring for that which produces those results. He illustrates this by referring to the fable of the goose that laid the golden eggs. He further claims that effectiveness can be expressed in terms of the P/PC ratio, where P refers to getting desired results and PC is caring for that which produces the results. "));

        books.add(new Book(8,304,"One Up on Wall Street","Peter Lynch","https://m.media-amazon.com/images/I/51RCSkqt7PL.jpg","Penned by the famous mutual-fund manager, Peter Lynch, this book elaborates the many advantages that an average investor has over professionals and how they can help them reach financial triumph.","Penned by the famous mutual-fund manager, Peter Lynch, this book elaborates the many advantages that an average investor has over professionals and how they can help them reach financial triumph. " +
                "\n" + "How To Use What You Already Know To Make Money in The Market explains how your knowledge alone can assist you beat the pros of investing. From the viewpoint of America's most triumphant money manager, investment chances are extensively accessible. Whether supermarket or work place, you can find goods and services everywhere. " +
                "\n" + "You have to select these organizations in which to invest, before they are found by skilled analysts. You will find more interesting knowledge on investment. Thus the book has become one of the best seller and treasure among readers. Moreover, this book provides time less recommendation on money business. " +
                "\n" + "This book has discussed the tips, ebb and flows on building it big in the investment market."));

        books.add(new Book(9,224,"The Subtle Art of Not Giving a Fuck","Mark Manson","https://images-na.ssl-images-amazon.com/images/I/71QKQ9mwV7L.jpg","In this generation-defining self-help guide, a superstar blogger cuts through the crap to show us how to stop trying to be “positive” all the time so that we can truly become better, happier people","When most people envision giving no fucks whatsoever, they envision a kind of perfect and serene indifference to everything, a calm that weathers all storms.\n" +
                "\n" +
                "This is misguided. There’s absolutely nothing admirable or confident about indifference. People who are indifferent are lame and scared. They’re couch potatoes and internet trolls. In fact, indifferent people often attempt to be indifferent because in reality they actually give too many fucks. " +
                "\n" + "They are afraid of the world and the repercussions of their own choices. Therefore, they make none. They hide in a grey emotionless pit of their own making, self-absorbed and self-pitied, perpetually distracting themselves from this unfortunate thing demanding their time and energy called life."));

        books.add(new Book(10,162,"Ikigai","Hector Garcia","https://images-na.ssl-images-amazon.com/images/I/71tbalAHYCL.jpg","THE INTERNATIONAL BESTSELLER We all have an ikigai. It's the Japanese word for ‘a reason to live’ or ‘a reason to jump out of bed in the morning’. It’s the place where your needs, desires, ambitions, and satisfaction meet. A place of balance."," THE INTERNATIONAL BESTSELLER We all have an ikigai. It's the Japanese word for ‘a reason to live’ or ‘a reason to jump out of bed in the morning’. It’s the place where your needs, desires, ambitions, and satisfaction meet. " +
                "\n" + "A place of balance. Small wonder that finding your ikigai is closely linked to living longer. Finding your ikigai is easier than you might think. This book will help you work out what your own ikigai really is, and equip you to change your life. " +
                "\n" + "You have a purpose in this world: your skills, your interests, your desires and your history have made you the perfect candidate for something. All you have to do is find it. Do that, and you can make every single day of your life joyful and meaningful. ‘I read it and it’s bewitched me ever since. I’m spellbound.’ " +
                "\n' + Chris Evans 'Ikigai gently unlocks simple secrets we can all use to live long, meaningful, happy lives. Science-based studies weave beautifully into honest, straight-talking conversation you won’t be able to put down. Warm, patient, and kind, this book pulls you gently along your own journey rather than pushing you from behind.' Neil Pasricha, bestselling author of The Happiness Equation\n"));

        books.add(new Book(11,167,"The Almanack Of Naval Ravikant","Eric Jorgenson","https://m.media-amazon.com/images/I/31EQXd8E9eL.jpg","Naval Ravikant is an entrepreneur, philosopher, and investor who has captivated the world with his principles for building wealth and creating long-term happiness. The Almanack of Naval Ravikant is a collection of Naval's wisdom and experience from the last ten years, shared as a curation of his most insightful interviews and poignant reflections.",
                "Getting rich is not just about luck; happiness is not just a trait we are born with. These aspirations may seem out of reach, but building wealth and being happy are skills we can learn.So what are these skills, and how do we learn them? What are the principles that should guide our efforts? What does progress really look like?" +
                        "\n" + "Naval Ravikant is an entrepreneur, philosopher, and investor who has captivated the world with his principles for building wealth and creating long-term happiness. The Almanack of Naval Ravikant is a collection of Naval's wisdom and experience from the last ten years, shared as a curation of his most insightful interviews and poignant reflections. " +
                        "\n" + "This isn't a how-to book, or a step-by-step gimmick. Instead, through Naval's own words, you will learn how to walk your own unique path toward a happier, wealthier life."));

        books.add(new Book(12,137,"Deep Work","Cal Newport","https://images-na.ssl-images-amazon.com/images/I/814IUt6WOKL.jpg","Cal Newport discusses in his new book, Deep Work: Rules For Focused Success In A Distracted World, about how professionals of today have started valuing quantity over quality; and how this has turned young professionals of today into puppets who try to indulge in extensive multitasking, dealing with multiple emails and projects.","Cal Newport discusses in his new book, Deep Work: Rules For Focused Success In A Distracted World, about how professionals of today have started valuing quantity over quality; and how this has turned young professionals of today into puppets who try to indulge in extensive multitasking, dealing with multiple emails and projects. " +
                "\n" + "This prevents them from doing 'deep work'; which is focused work free from all other distractions. This also means that the professionals of today should sort out their priorities. Newport uses principles of psychology and neuroscience to enhance his points. He elaborates how to improve a person's cognitive abilities and how employers should encourage workers to not take shortcuts for completing projects. " +
                "\n" + "He claims that the best way to break away from the corporate race is to take a break from technology and social media and use some alone-time to rewind and introspect. Newport enforces the beliefs of a non-technophile to deliver work that is productive and efficiently delivered."));

        books.add(new Book(13,688,"MONEY Master the Game","Tony Robbins","https://images-na.ssl-images-amazon.com/images/I/81ZzMloJuFL.jpg","Tony Robbins. Robbins has a brilliant way of using metaphor and story to illustrate even the most complex financial concepts - making them simple and actionable. With expert advice on our most important financial decisions, Robbins is an advocate for the reader, dispelling the myths that often rob people of their financial dreams. ","\n" +
                "Multimillion-copy bestselling author of Awaken the Giant Within and Unlimited Power has created a new 7-step blueprint for securing financial freedom. Based on extensive research and one-on-one interviews with more than 50 of the most legendary financial experts in the world - from Carl Icahn, to Warren Buffett, to Jack Bogle and Steve Forbes, Tony Robbins. " +
                "\n" + "Robbins has a brilliant way of using metaphor and story to illustrate even the most complex financial concepts - making them simple and actionable. With expert advice on our most important financial decisions, Robbins is an advocate for the reader, dispelling the myths that often rob people of their financial dreams. Tony Robbins walks readers of every income level, through the steps to become financially free by creating a lifetime income plan. " +
                "\n" + "This book delivers invaluable information and essential practices for getting your financial house in order. It's the book millions of people have been waiting for. "));

        books.add(new Book(14,155,"Little Book of Common Sense Investing","John C. Bogle","https://m.media-amazon.com/images/I/51wvE48u69L.jpg","The Little Book of Common Sense Investing is the classic guide to getting smart about the market. Legendary mutual fund  pioneer John C. Bogle reveals his key to getting more out of investing: low-cost index funds.",
                "The Little Book of Common Sense Investing is the classic guide to getting smart about the market. Legendary mutual fund  pioneer John C. Bogle reveals his key to getting more out of investing: low-cost index funds. Bogle describes the simplest and most effective investment strategy for building wealth over the long term: buy and hold, at very low cost, a mutual fund that tracks a broad stock market Index such as the S&P 500.\n" +
                        "\n" +
                        "While the stock market has tumbled and then soared since the first edition of Little Book of Common Sense was published in April 2007, Bogle’s investment principles have endured and served investors well.  This tenth anniversary edition includes updated data and new information but maintains the same long-term perspective as in its predecessor. \n" +
                        "\n" +
                        "Bogle has also added two new chapters designed to provide further guidance to investors:  one on asset allocation, the other on retirement investing." +
                        "\n A portfolio focused on index funds is the only investment that effectively guarantees your fair share of stock market returns. This strategy is favored by Warren Buffett, who said this about Bogle: “If a statue is ever erected to honor the person who has done the most for American investors, the hands-down choice should be Jack Bogle. For decades, Jack has urged investors to invest in ultra-low-cost index funds. . . . Today, however, he has the satisfaction of knowing that he helped millions of investors realize far better returns on their savings than they otherwise would have earned. He is a hero to them and to me." +
                        "Bogle shows you how to make index investing work for you and help you achieve your financial goals, and finds support from some of the world's best financial minds: not only Warren Buffett, but Benjamin Graham, Paul Samuelson, Burton Malkiel, Yale’s David Swensen, Cliff Asness of AQR, and many others." +
                        "This new edition of TheLittle Book of Common Sense Investing offers you the same solid strategy as its predecessor for building your financial future." +
                        "    Build a broadly diversified, low-cost portfolio without the risks of individual stocks, manager selection, or sector rotation.\n" +
                        "    Forget the fads and marketing hype, and focus on what works in the real world.\n" +
                        "    Understand that stock returns are generated by three sources (dividend yield, earnings growth, and change in market valuation) in order to establish rational expectations for stock returns over the coming decade.\n" +
                        "    Recognize that in the long run, business reality  trumps market expectations.\n" +
                        "    Learn how to harness the magic of compounding returns while avoiding the tyranny of compounding costs.\n" +
                        "\n" +
                        "While index investing allows you to sit back and let the market do the work for you, too many investors trade frantically, turning a winner’s game into a loser’s game. The Little Book of Common Sense Investing is a solid guidebook to your financial future."));

        books.add(new Book(15,350,"The Four Pillars of Investing: Lessons for Building a Winning Portfolio","William J. Bernstein","https://m.media-amazon.com/images/I/51hlBGKZJzL.jpg","This down-to-earth book lays out in easy-to-understand prose the four essential topics that every investor must master: the relationship of risk and reward, the history of the market, the psychology of the investor and the market, and the folly of taking financial advice from investment salespeople.",
                "\n" + "The classic guide to constructing a solid portfolio―without a financial advisor!\n" +
                        "\n" +
                        "“With relatively little effort, you can design and assemble an investment portfolio that, because of its wide diversification and minimal expenses, will prove superior to the most professionally managed accounts. Great intelligence and good luck are not required.”\n" +
                        "\n" +
                        "William Bernstein’s commonsense approach to portfolio construction has served investors well during the past turbulent decade―and it’s what made The Four Pillars of Investing an instant classic when it was first published nearly a decade ago.\n" +
                        "\n" +
                        "This down-to-earth book lays out in easy-to-understand prose the four essential topics that every investor must master: the relationship of risk and reward, the history of the market, the psychology of the investor and the market, and the folly of taking financial advice from investment salespeople.\n" +
                        "\n" +
                        "Bernstein pulls back the curtain to reveal what really goes on in today’s financial industry as he outlines a simple program for building wealth while controlling risk. Straightforward in its presentation and generous in its real-life examples, The Four Pillars of Investing presents a no-nonsense discussion of:\n" +
                        "\n" +
                        "    The art and science of mixing different asset classes into an effective blend\n" +
                        "    The dangers of actively picking stocks, as opposed to investing in the whole market\n" +
                        "    Behavioral finance and how state of mind can adversely affect decision making\n" +
                        "    Reasons the mutual fund and brokerage industries, rather than your partners, are often your most direct competitors\n" +
                        "    Strategies for managing all of your assets―savings, 401(k)s, home equity―as one portfolio\n" +
                        "\n" +
                        "Investing is not a destination. It is a journey, and along the way are stockbrokers, journalists, and mutual fund companies whose interests are diametrically opposed to yours.\n" +
                        "\n" +
                        "More relevant today than ever, The Four Pillars of Investing shows you how to determine your own financial direction and assemble an investment program with the sole goal of building long-term wealth for you and your family.\n"));

        books.add(new Book(16,209,"The Psychology of Money: Timeless Lessons on Wealth, Greed, and Happiness","Morgan Housel","https://images-na.ssl-images-amazon.com/images/I/81Lb75rUhLL.jpg","In The Psychology of Money, award-winning author Morgan Housel shares 19 short stories exploring the strange ways people think about money and teaches you how to make better sense of one of life’s most important topics.",
                "\n" +
                        "Doing well with money isn’t necessarily about what you know. It’s about how you behave. And behavior is hard to teach, even to really smart people.\n" +
                        "\n" +
                        "Money―investing, personal finance, and business decisions―is typically taught as a math-based field, where data and formulas tell us exactly what to do. But in the real world people don’t make financial decisions on a spreadsheet. They make them at the dinner table, or in a meeting room, where personal history, your own unique view of the world, ego, pride, marketing, and odd incentives are scrambled together.\n" +
                        "\n" +
                        "In The Psychology of Money, award-winning author Morgan Housel shares 19 short stories exploring the strange ways people think about money and teaches you how to make better sense of one of life’s most important topics.\n"));

        books.add(new Book(17,154,"The Alchemist","Paulo Coelho","https://images-na.ssl-images-amazon.com/images/I/71aFt4+OTOL.jpg","The most prominent theme in The Alchemist is the idea that each person has a “Personal Legend”—a type of ideal fate or destiny—and that each person can chose whether or not to pursue that legend.","Paulo Coelho's enchanting novel has inspired a devoted following around the world. " +
                "\n" +
                "This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago, who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. "));

        books.add(new Book(18,258 ,"The Power of Now: A Guide to Spiritual Enlightenment","Eckhart Tolle","https://images-na.ssl-images-amazon.com/images/I/714FbKtXS+L.jpg","The Power of Now shows you that every minute you spend worrying about the future or regretting the past is a minute lost, because really all you have to live in is the present, the now, and gives you actionable strategies to start living every minute as it occurs."," It's no wonder that The Power of Now has sold over 2 million copies worldwide and has been translated into over 30 foreign languages. " +
                "\n" + "Much more than simple principles and platitudes, the book takes readers on an inspiring spiritual journey to find their true and deepest self and reach the ultimate in personal growth and spirituality: the discovery of truth and light.\n" +
                "\n" +
                "In the first chapter, Tolle introduces readers to enlightenment and its natural enemy, the mind. He awakens readers to their role as a creator of pain and shows them how to have a pain-free identity by living fully in the present. The journey is thrilling, and along the way, the author shows how to connect to the indestructible essence of our Being, \"the eternal, ever-present One Life beyond the myriad forms of life that are subject to birth and death.\"\n" +
                "\n" +
                "Featuring a new preface by the author, this paperback shows that only after regaining awareness of Being, liberated from Mind and intensely in the Now, is there Enlightenment. "));

        books.add(new Book(19,224,"Zero to One","Peter Thiel","https://images-na.ssl-images-amazon.com/images/I/71Xygne8+qL.jpg","This book delivers completely new and refreshing ideas on how to create value in the world. Peter Thiel has built multiple breakthrough companies, and Zero to One shows how.","The great secret of our time is that there are still uncharted frontiers to explore and new inventions to create. In Zero to One, legendary entrepreneur and investor Peter Thiel shows how we can find singular ways to create those new things.\n" +
                "\n" +
                "Thiel begins with the contrarian premise that we live in an age of technological stagnation, even if we’re too distracted by shiny mobile devices to notice. Information technology has improved rapidly, but there is no reason why progress should be limited to computers or Silicon Valley. Progress can be achieved in any industry or area of business. It comes from the most important skill that every leader must master: learning to think for yourself.\n" +
                "\n" +
                "Doing what someone else already knows how to do takes the world from 1 to n, adding more of something familiar. But when you do something new, you go from 0 to 1. The next Bill Gates will not build an operating system. The next Larry Page or Sergey Brin won’t make a search engine. Tomorrow’s champions will not win by competing ruthlessly in today’s marketplace. They will escape competition altogether, because their businesses will be unique.\n" +
                "\n" +
                "Zero to One presents at once an optimistic view of the future of progress in America and a new way of thinking about innovation: it starts by learning to ask the questions that lead you to find value in unexpected places."));

        books.add(new Book(20,80,"Meditations","Marcus Aurelius","https://m.media-amazon.com/images/I/418Fn-qJVAL.jpg","Meditations is a collection of 12 books written by Roman emperor Marcus Aurelius, who’ll introduce you to Stoic philosophy, the concept of logic, self-discipline and give you faith that the course the world runs is a good one.","Written in Greek by the only Roman emperor who was also a philosopher, without any intention of publication, the Meditations of Marcus Aurelius offer a remarkable series of challenging spiritual reflections and exercises developed as the emperor struggled to understand himself and make sense of the universe. " +
                "\n" + "While the Meditations were composed to provide personal consolation and encouragement, Marcus Aurelius also created one of the greatest of all works of philosophy: a timeless collection that has been consulted and admired by statesmen, thinkers and readers throughout the centuries. "));

        books.add(new Book(21,200,"Man's Search for Meaning","Viktor Frankl","https://m.media-amazon.com/images/I/41s4xJZlEYL.jpg","This deeply inspirational story of triumph of the human spirit over the evils of the Nazi regime, tells the courageous story of a Holocaust survivor, determined to live through his ordeal. His remarkable secret to salvation was to deeply imagine his life having a higher purpose. " +
                "\n" + "This belief allowed him to survive the horrors of the Nazi concentration camp he was confined to.","A prominent Viennese psychiatrist before the war, Viktor Frankl was uniquely able to observe the way that he and other inmates coped with the experience of being in Auschwitz. " +
                "\n" + "He noticed that it was the men who comforted others and who gave away their last piece of bread who survived the longest - and who offered proof that everything can be taken away from us except the ability to choose our attitude in any given set of circumstances. The sort of person the prisoner became was the result of an inner decision and not of camp influences alone. " +
                "\n" + "Only those who allowed their inner hold on their moral and spiritual selves to subside eventually fell victim to the camp's degenerating influence - while those who made a victory of those experiences turned them into an inner triumph. Frankl came to believe that man's deepest desire is to search for meaning and purpose. This outstanding work offers us all a way to transcend suffering and find significance in the art of living."));

        books.add(new Book(22,224,"The Obstacle is the Way","Ryan Holiday","https://images-na.ssl-images-amazon.com/images/I/71Za+yDrvTL.jpg","The words of Roman Emperor and philosopher Marcus Aurelius highlight a truth any high achiever has lived by, obstacles are opportunities in disguise. “The Obstacles in the Way” provides a recipe to follow so anyone can triumph in the face of adversity. The stories of great inventors, entrepreneurs and pioneers of their time who overcame obstacles are analyzed so anyone can emulate their success.",
                "\n" +
                "The Obstacle is the Way has become a cult classic, beloved by men and women around the world who apply its wisdom to become more successful at whatever they do. " +
                        "\n" + "The book draws its inspiration from stoicism, the ancient Greek philosophy of enduring pain or adversity with perseverance and resilience. Stoics focus on the things they can control, let go of everything else, and turn every new obstacle into an opportunity to get better, stronger, tougher. As Marcus Aurelius put it nearly 2000 years ago: “The impediment to action advances action. What stands in the way becomes the way.” \n" +
                        "\n" +
                        "Ryan Holiday shows us how some of the most successful people in history—from John D. Rockefeller to Amelia Earhart to Ulysses S. Grant to Steve Jobs—have applied stoicism to overcome difficult or even impossible situations. Their embrace of these principles ultimately mattered more than their natural intelligence, talents, or luck.\n" +
                        "\n" +
                        "If you’re feeling frustrated, demoralized, or stuck in a rut, this book can help you turn your problems into your biggest advantages. And along the way it will inspire you with dozens of true stories of the greats from every age and era." ));

        books.add(new Book(23,208,"The Compound Effect","Darren Hardy","https://images-na.ssl-images-amazon.com/images/I/51Bz60iDotL.jpg","The Compound Effect teaches those looking for a radical overhaul of their life how to get on top of their finances by choosing to hold themselves accountable for everything that happens to them. " +
                "\n" + "Hardy teaches readers how to take control of their life with small steps every day. After reading “The Compound Effect” anyone will be inspired to ditch their excuses so they can execute.","\n" +
                "\n" +
                "No gimmicks. No Hyperbole. No Magic Bullet. The Compound Effect is based on the principle that decisions shape your destiny. Little, everyday decisions will either take you to the life you desire or to disaster by default. Darren Hardy, publisher of Success Magazine, presents The Compound Effect, a distillation of the fundamental principles that have guided the most phenomenal achievements in business, relationships, and beyond. This easy-to-use, step-by-step operating system allows you to multiply your success, chart your progress, and achieve any desire. " +
                "\n" + "If you’re serious about living an extraordinary life, use the power of The Compound Effect to create the success you want.\n"));

        books.add(new Book(24,500,"12 Rules for life","Jordan Peterson","https://images-na.ssl-images-amazon.com/images/I/41kspFBwVxL._SX331_BO1,204,203,200_.jpg","The 12 Rules For Life is a candid, accessible, well-researched self-help instruction guide for anyone questioning how to best engineer the life they desire using a simple set of rules. Peterson’s multifaceted approach uses science, storytelling, psychology and philosophy to explain how each rule will benefit the reader and how to incorporate each rule into their everyday life."
        ,"Deep, rewarding and enlightening, 12 Rules for Life is a lifeboat built solidly for stormy seas: ancient wisdom applied to our contemporary problems." +
                "\n" + "The book is divided into chapters with each title representing one of the following twelve specific rules for life as explained through an essay.\n" +
                "\n" +
                "1    \"Stand up straight with your shoulders back.\"\n" +
                "2    \"Treat yourself like you are someone you are responsible for helping.\"\n" +
                "3    \"Make friends with people who want the best for you.\"\n" +
                "4    \"Compare yourself to who you were yesterday, not to who someone else is today.\"\n" +
                "5    \"Do not let your children do anything that makes you dislike them.\"\n" +
                "6    \"Set your house in perfect order before you criticize the world.\"\n" +
                "7    \"Pursue what is meaningful (not what is expedient).\"\n" +
                "8    \"Tell the truth – or, at least, don’t lie.\"\n" +
                "9    \"Assume that the person you are listening to might know something you don’t.\"\n" +
                "10    \"Be precise in your speech.\"\n" +
                "11    \"Do not bother children when they are skateboarding.\"\n" +
                "12    \"Pet a cat when you encounter one on the street.\""));

        books.add(new Book(25,480,"The 48 Laws of Power","Robert Greene","https://images-na.ssl-images-amazon.com/images/I/71aG+xDKSYL.jpg","The 48 Laws of Power is a self-help book and Robert Greene's first book. This book evolved from Robert Greene's association with a book packager named Joost Elffers. Together, they framed the 48 laws which dictated who would have most power.",
                "Robert Greene and Joost Elffers have distilled three thousand years of the history of power into 48 essential laws by drawing from the philosophies of Machiavelli, Sun Tzu, and Carl Von Clausewitz and also from the lives of figures ranging from Henry Kissinger to P.T. Barnum.\n" +
                        " \n" +
                        "Some laws teach the need for prudence (“Law 1: Never Outshine the Master”), others teach the value of confidence (“Law 28: Enter Action with Boldness”), and many recommend absolute self-preservation (“Law 15: Crush Your Enemy Totally”). Every law, though, has one thing in common: an interest in total domination. In a bold and arresting two-color package, The 48 Laws of Power is ideal whether your aim is conquest, self-defense, or simply to understand the rules of the game."));

        books.add(new Book(26,224,"The Power of Decision","Raymond Charles Barker","https://m.media-amazon.com/images/I/51WTz+P2BXL.jpg","Power of Decision is an intellectual guide applicable to all because we all have to make decisions in life. Choices are presented to each one of us and with choices comes choosing which path to take. In Power of Decision, the mystery and fear is dismantled so that all decisions are correct.","Introducing the first book in a powerful new series, The Tarcher Master Mind Editions: Essential Books of Inspiration, Instruction, and Motivation.\n" +
                "\n" +
                "What mind can conceive, man can achieve.\n" +
                "\n" +
                "Our decisions impact every area of our lives. Making better decisions means living a better life. But how can we develop the habit of making great decisions?\n" +
                "\n" +
                "Every noteworthy achievement the world has ever seen was born with a single thought; and every great man who ever lived has been a man of decision. Raymond Charles Barker's The Power of Decision reveals this principle of success and illustrates the process of choice that all of us must take-and that all of us are capable, this very second, of taking-to change our lives and make our dreams come true.\n" +
                "\n" +
                "Indecisive people are failure prone, and Dr. Barker examines this basic truth while exploring the decision-making process in the individual, and the role of the subconscious mind in either abetting or thwarting each of our conscious decisions. He provides specific steps to shift the balance of decision-making power in your favor, and he brings to light the constant, ever-present power of will to change a situation- and yourself-for the better.\n" +
                "\n" +
                "Picking up The Power of Decision is the moment; and reading it is the decision that will change your life forever."));

        books.add(new Book(27,320,"Mindset: The New Psychology of Success ","Carol Dweck","https://images-na.ssl-images-amazon.com/images/I/81I68HH7a2L.jpg","MINDSET AFFECTS SUCCESS, FAILURE, ATTITUDES & EFFORT" +
                "\n" + "People with a fixed mindset believe their abilities are fixed, thus they prefer to stay in their comfort zone and focus on validating and proving themselves. Those with a growth mindset focus on learning and stretching themselves."," From the renowned psychologist who introduced the world to “growth mindset” comes this updated edition of the million-copy bestseller—featuring transformative insights into redefining success, building lifelong resilience, and supercharging self-improvement.\n" +
                "\n" +
                "“Through clever research studies and engaging writing, Dweck illuminates how our beliefs about our capabilities exert tremendous influence on how we learn and which paths we take in life.”—Bill Gates, GatesNotes\n" +
                "\n" +
                "“It’s not always the people who start out the smartest who end up the smartest.”\n" +
                "\n" +
                "After decades of research, world-renowned Stanford University psychologist Carol S. Dweck, Ph.D., discovered a simple but groundbreaking idea: the power of mindset. In this brilliant book, she shows how success in school, work, sports, the arts, and almost every area of human endeavor can be dramatically influenced by how we think about our talents and abilities. People with a fixed mindset—those who believe that abilities are fixed—are less likely to flourish than those with a growth mindset—those who believe that abilities can be developed. Mindset reveals how great parents, teachers, managers, and athletes can put this idea to use to foster outstanding accomplishment.\n" +
                "\n" +
                "In this edition, Dweck offers new insights into her now famous and broadly embraced concept. She introduces a phenomenon she calls false growth mindset and guides people toward adopting a deeper, truer growth mindset. She also expands the mindset concept beyond the individual, applying it to the cultures of groups and organizations. With the right mindset, you can motivate those you lead, teach, and love—to transform their lives and your own. "));

        books.add(new Book(28,256,"The Art of War","Sun Tzu","https://images-na.ssl-images-amazon.com/images/I/91jwTL9mXHL.jpg","The Art of War is an ancient Chinese military treatise dating from the Late Spring and Autumn Period. The work, which is attributed to the ancient Chinese military strategist Sun Tzu, is composed of 13 chapters.","Twenty-Five Hundred years ago, Sun Tzu wrote this classic book of military strategy based on Chinese warfare and military thought. Since that time, all levels of military have used the teaching on Sun Tzu to warfare and civilization have adapted these teachings for use in politics, business and everyday life. " +
                "\n" + "The Art of War is a book which should be used to gain advantage of opponents in the boardroom and battlefield alike."));

        books.add(new Book(29,352,"The Art of Happiness","Dalai Lama","https://m.media-amazon.com/images/I/414xA5K3xoL.jpg","The Art of Happiness is a guide to Eastern spiritual traditions for a Western audience. This book covers all facets of the human experience, including how to solve everyday problems so that you can live a happy life. Plus, how you can remain peaceful during highly stressful and challenging times.",
                "In this unique and important book, one of the world's great spiritual leaders offers his practical wisdom and advice on how we can overcome everyday human problems and achieve lasting happiness.The Art of Happiness is a highly accessible guide for a western audience, combining the Dalai Lama's eastern spiritual tradition with Dr Howard C. Cutler's western perspective. " +
                "\n" + "Covering all key areas of human experience, they apply the principles of Tibetan Buddhism to everyday problems and reveal how one can find balance and complete spiritual and mental freedom.For the many who wish to understand more about the Dalai Lama's approach to living, there has never been a book which brings his beliefs so vividly into the real world."));

        books.add(new Book(30,216,"The Big Leap","Gay Hendricks","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1348335194l/6391876.jpg","“The Big Leap” is a book about dreams and fears, and how beating the fears and self-imposed limits can make all of those dreams come true. We recommend this read to people who are trying to find the direction of their lives, or who are not satisfied with how life plays out so far, and want to transform their reality.",
                "\n" +
                "\n" +
                "“Gay Hendricks is a great role model for true success. He enjoys abundance and a deep connection with his own spiritual essence, and at the same time has lived for three decades in a thriving marriage. Now, he shows us how to do it for ourselves.”\n" +
                "— Mark Victor Hansen, co-author of Cracking the Millionaire Code\n" +
                "\n" +
                "In The Big Leap, Gay Hendricks, the New York Times bestselling author of Five Wishes, demonstrates how to eliminate the barriers to success by overcoming false fears and beliefs. Fans of Wayne Dyer, Eckhart Tolle, Marianne Williamson, and The Secret will find useful, effective tips for breaking down the walls to a better life in The Big Leap.\n"));
        books.add(new Book(31,368,"Who Says You can't? you do","Daniel Chidiac","https://images-na.ssl-images-amazon.com/images/I/51bLFVR3GZL._SX311_BO1,204,203,200_.jpg","A word-of-mouth phenomenon that's changing lives around the world--a journey into your true self and amazing potential.Do you want to change your life? Well, who says you can't? ",
                "A word-of-mouth phenomenon that's changing lives around the world--a journey into your true self and amazing potential.Do you want to change your life? Well, who says you can't? A moment came in Daniel Chidiac's life when he realized he wasn't living his truth. His work didn't fulfill him, his relationships hurt him, and he was making choices that didn't align with his true values. But he did have the ability to know his own purpose--a gift we all have--and thus his journey began." +
                        "\n" + "Daniel studied the lives of great achievers, sought guidance from spiritual leaders, and discovered the secrets for shaping one's own destiny.\n" +
                        "He used his personal experience of changing his life to create this powerful seven-step guide to discovering your true self, committing to your own life, and pushing beyond your known limits.Standing out for his incisive wisdom and complete lack of gimmicks, Daniel Chidiac is an inspiring, insightful, and honest guide. His empowering system has spread organically, and it has already changed the lives of legions of readers. " +
                        "\n" + "With practical exercises and interactive tools, this book challenges you to ask hard questions and make life-changing decisions--and ultimately guides you to the fulfillment you have been seeking. Get ready to be intrigued, fascinated, and amazed. Not by this book, but by your own power.\n" +
                        " "));
        books.add(new Book(32,196,"What if It Does Work Out?","Susie Moore","https://images-na.ssl-images-amazon.com/images/I/61gIYR9mexL.jpg","Having a sense of meaning and purpose is key to a thriving life. Susie Moore not only helps you discover your own purpose, but offers a practical guide to making it the driving force in your life and work.",
                "Do you have a hobby or passion that has nothing to do with your nine-to-five job? Do you craft vintage jewelry, make handmade furniture, or offer expert negotiating advice to family and friends in your spare time? Then you, too, could join the 1/3 of Americans who turn their talents into a lucrative side hustle.\n" +
                        "\n" + "In What If It Does Work Out? life coach and professional side-hustler Susie Moore offers expert tips and guidance to help you earn an extra source of income by doing something you love. In her energetic and encouraging style, she guides you through all of the planning stages and potential obstacles, showing how to overcome any hesitation or fear, create multiple revenue streams, and more. " +
                        "\n" + "Susie also presents inspiring stories from fellow side hustle successes, including the founders of Spanx and MindBodyGreen. Recommended by Entrepreneur magazine as a book \"entrepreneurs must read to dominate their industry,\" What If It Does Work Out? features all you need to take the practical steps toward living the life of your dreams. "));

        books.add(new Book(33,101,"Chop Wood Carry Water","Joshua Medcaif","https://images-na.ssl-images-amazon.com/images/I/51iSli19n3L.jpg","Guided by “Akira-sensei,” John comes to realize the greatest adversity on his journey will be the challenge of defeating the man in the mirror. " +
                "\n" + "This powerful story of one boy’s journey to achieve his life long goal of becoming a samurai warrior, brings the Train to be CLUTCH curriculum to life in a powerful and memorable way. ",
                "Guided by “Akira-sensei,” John comes to realize the greatest adversity on his journey will be the challenge of defeating the man in the mirror. This powerful story of one boy’s journey to achieve his life long goal of becoming a samurai warrior, brings the Train to be CLUTCH curriculum to life in a powerful and memorable way. " +
                        "\n" + "Some things you will learn... —No matter how it feels, you are always building your own house. —How and why you must surrender to the outcome in order to be at your best. —Why you never want to have your identity wrapped up in what you do. —Why your strength lies in faithfulness to the little things. —How to develop a heart posture of gratitude. " +
                        "\n" + "—How to use the biggest challenges as a training ground for greatness. —Why the process is more important than the goal. —Why comparison is the thief of all joy. —How to develop a growth mindset. —Why talent is more of a curse than a blessing. “So many valuable stories and lessons!” —Nick Ahmed, Arizona Diamondbacks"));

        books.add(new Book(34,158,"Motivational Moments","Jason Wilbanks Brown","https://images-na.ssl-images-amazon.com/images/I/51R1dhHGMJL._SX331_BO1,204,203,200_.jpg","Are you popular, powerful, a perfectionist, or peaceful? Are you a little of all 4? Whether you are a company bigwig or king of the kitchen, Motivational Moments will help you understand why people (you included) say what they say and do what they do. " +
                "\n" + "Using the Ancient Greeks' Four Humors personality decoder, uncover the secrets of your own and other's personalities using 52 sets of stories, fun facts, and challenges to help you become a better person",
                "Are you popular, powerful, a perfectionist, or peaceful? Are you a little of all 4? Whether you are a company bigwig or king of the kitchen, Motivational Moments will help you understand why people (you included) say what they say and do what they do. Using the Ancient Greeks' Four Humors personality decoder, uncover the secrets of your own and other's personalities using 52 sets of stories, fun facts, and challenges to help you become a better"));

        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        Gson gson1 = new Gson();
        editor1.putString(ALL_BOOKS_KEY, gson1.toJson(books));
        editor1.commit();
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson1.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson1.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson1.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null), type);
        return books;
    }

    public ArrayList<Book> getWishListBooks() {
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson1.fromJson(sharedPreferences.getString(WISHLIST_BOOKS,null), type);
        return books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson1.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null), type);
        return books;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b: books) {
                if(b.getId() == id){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson1 = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson1.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishListBooks(Book book){
        ArrayList<Book> books = getWishListBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson1 = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_BOOKS);
                editor.putString(WISHLIST_BOOKS, gson1.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavouriteBooks(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson1 = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson1.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson1 = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson1.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyReadBooks (Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            for (Book b:books
                 ) {
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson1 = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson1.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReadingBooks (Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            for (Book b:books
            ) {
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson1 = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson1.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWishListBooks (Book book){
        ArrayList<Book> books = getWishListBooks();
        if (null != books){
            for (Book b:books
            ) {
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson1 = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST_BOOKS);
                        editor.putString(WISHLIST_BOOKS, gson1.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavouriteBooks (Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books){
            for (Book b:books
            ) {
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson1 = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS, gson1.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


