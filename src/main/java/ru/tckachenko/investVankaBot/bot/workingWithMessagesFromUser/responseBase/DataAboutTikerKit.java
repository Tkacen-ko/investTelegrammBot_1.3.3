package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.List;
import java.util.Locale;

public class DataAboutTikerKit extends AnswerWithTextOnly {
    public String message;
    String tikerName;
    List<TiketInformation> getNameTiker;
    public DataAboutTikerKit(String tikerName, List<TiketInformation> getNameTiker){
        this.tikerName = tikerName;
        this.getNameTiker = getNameTiker;
    }


    @Override
    public String getMessage() {
        for(TiketInformation tiketInformation : getNameTiker){
            System.out.println(tiketInformation.getTiket().toLowerCase());
            System.out.println(tikerName);
            System.out.println(tiketInformation.getTiket().toLowerCase().equals(tikerName));
            if(tiketInformation.getTiket().toLowerCase().equals(tikerName)){
                return message = tiketInformation.getTiket() + " (" + tiketInformation.getNameCompany() + " )"+
                        "данный тикер относиться к категории "  + getRankTikerReaction(tiketInformation.getRankTiket())+
                        "Сегодня данная ценная бумага показывает" + todayGrowth(tiketInformation.getChangesday())+"("+tiketInformation.getChangesday()+"), "+
                        "в течении месяца происходит" + monshGrowth(tiketInformation.getChangesmonth())+"("+tiketInformation.getChangesmonth()+"), "+
                       "а в течении год тикер "+ yearGrowth(tiketInformation.getChangesyear())+"("+tiketInformation.getChangesyear()+")." ;

            }
        }
        //!пробижаться по все тикерам в переменной getNameTiker и в том случае если будет найдено совпадение вызвать то что нужно

        return "Где то чет почему, сорян";
    }
    public String getRankTikerReaction(String RankTiker){
        switch(RankTiker) {
            case "gold":
                return "лидеров по капитализации, а значит обладает " +
                        "не слишком высокой волатильностью и отличается " +
                        "более высокой надёжностью.\n";
            case "silver":
            case "bronze":
                return "капиталистов средней руки, соответственно " +
                        "может иметь средние и высокие колебания " +
                        "с стоимость акций на рынке.\n";
            case "dno":
                return "компаний малого рынка, стоимость бумаг может " +
                        "значительно меняться как на большом так и " +
                        "на малом горизонте времени.\n";
            default:
                return "!Братан! ты где то облажался и в качестве реакции" +
                        "пришла какая то параша в виде меня =(!";
        }

    }
    public String todayGrowth(double todayGrowth){
        if(todayGrowth<-10){
            return "значительное падение";
        }
        if(todayGrowth>-10 && todayGrowth<-3){
            return "умеренное падение";
        }
        if(todayGrowth>-3 && todayGrowth<0){
            return "малое падение";
        }
        if(todayGrowth<3 && todayGrowth>0){
            return "малый рост";
        }
        if(todayGrowth<10 && todayGrowth>3){
            return "умеренный рост";
        }
        if(todayGrowth>10){
            return "значительный рост";
        }
        return "!Братан! ты где то облажался и в качестве реакции" +
                "пришла какая то параша в виде меня =(!";
    }
    public String monshGrowth(double monshGrowth){
        if(monshGrowth<-15){
            return "значительное падение";
        }
        if(monshGrowth>-15 && monshGrowth<-5){
            return "умеренное падение";
        }
        if(monshGrowth>-5 && monshGrowth<0){
            return "малое падение";
        }
        if(monshGrowth<5 && monshGrowth>0){
            return "малый рост";
        }
        if(monshGrowth<15 && monshGrowth>5){
            return "умеренный рост";
        }
        if(monshGrowth>15){
            return "значительный рост";
        }
        return "!Братан! ты где то облажался и в качестве реакции" +
                "пришла какая то параша в виде меня =(!";
    }
    public String yearGrowth(double yearGrowth){
        if(yearGrowth<-20){
            return "значительное потерял в стоимости";
        }
        if(yearGrowth>-20 && yearGrowth<-7){
            return "умеренно падал";
        }
        if(yearGrowth>-7 && yearGrowth<0){
            return "незначительно падал";
        }
        if(yearGrowth<7 && yearGrowth>0){
            return "незначительно рос";
        }
        if(yearGrowth<20 && yearGrowth>7){
            return "умеренно рос";
        }
        if(yearGrowth>20){
            return "значительно рос";
        }
        return "!Братан! ты где то облажался и в качестве реакции" +
                "пришла какая то параша в виде меня =(!";
    }

    @Override
    public List<String> getButtons() {return null;}
}
