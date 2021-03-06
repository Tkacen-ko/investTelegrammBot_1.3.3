package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.List;

public class DataAboutTikerKit extends AnswerWithTextOnly {
    private String message;
    String tickerName;
    List<TikerInformation> getNameTiker;

    public DataAboutTikerKit(String tickerName, List<TikerInformation> getNameTiker) {
        this.tickerName = tickerName;
        this.getNameTiker = getNameTiker;
    }


    @Override
    public String getMessage() {
        for (TikerInformation tikerInformation : getNameTiker) {
            if (tikerInformation.getTicker().toLowerCase().equals(tickerName)) {
                return message = "*" + tikerInformation.getTicker() + "*" + " (_" + tikerInformation.getNameCompany() + "_).\n\n" +
                        getSectorCompany(tikerInformation.getSector()) +
                        getSectorIndustry(tikerInformation.getIndustry()) +
                        getSectorCompanyDescription(tikerInformation.getCompanyDescription()) +
                        "Данный тикер относиться к категории " + getRankTikerReaction(tikerInformation.getRankTicker()) + "\n" +
                        "*Стоимость тикера на текущий момент* - _" + tikerInformation.getPrice() + "_ руб\n" +
                        "*Сегодня* данная ценная бумага показывает " + todayGrowth(tikerInformation.getChangesday()) +
                        "В течении *месяца* происходит " + monshGrowth(tikerInformation.getChangesmonth()) +
                        "В течении *года* тикер " + yearGrowth(tikerInformation.getChangesyear());

            }
        }
        return null;
    }

    public String getRankTikerReaction(String RankTiker) {
        switch (RankTiker) {
            case "gold":
                return "лидеров по капитализации, а значит обладает " +
                        "не слишком высокой волатильностью и отличается " +
                        "более высокой надёжностью.\n";
            case "silver":
            case "bronze":
                return "капиталистов средней уровня, соответственно " +
                        "может иметь средние и высокие колебания " +
                        "с стоимость акций на рынке.\n";
            case "dno":
                return "компаний малого рынка, стоимость бумаг может " +
                        "значительно меняться как на большом так и " +
                        "на малом горизонте времени.\n";
            default:
                return null;
        }

    }

    public String todayGrowth(double todayGrowth) {
        if (todayGrowth < -10) {
            return "*значительное падение* \uD83D\uDE2D " + " (" + todayGrowth + "%).\n";
        }
        if (todayGrowth > -10 && todayGrowth < -3) {
            return "*умеренное падение* \uD83D\uDE20 " + " (" + todayGrowth + "%).\n";
        }
        if (todayGrowth > -3 && todayGrowth < 0) {
            return "*малое падение* \uD83D\uDE12 " + " (" + todayGrowth + "%).\n";
        }
        if (todayGrowth < 3 && todayGrowth > 0) {
            return "*малый рост* \uD83D\uDE0C" + " (+" + todayGrowth + "%).\n";
        }
        if (todayGrowth < 10 && todayGrowth > 3) {
            return "*умеренный рост \uD83D\uDE0A *  " + " (+" + todayGrowth + "%).\n";
        }
        if (todayGrowth > 10) {
            return "*значительный рост* \uD83D\uDCB0 " + " (+" + todayGrowth + "%).\n";
        }
        if (todayGrowth == 0) {
            return "*не изменился в стоимости* (0%)\n";
        }

        return null;
    }

    public String monshGrowth(double monshGrowth) {
        if (monshGrowth < -15) {
            return "*значительное падение* \uD83D\uDE2D " + " (" + monshGrowth + "%).\n";
        }
        if (monshGrowth > -15 && monshGrowth < -5) {
            return "*умеренное падение* \uD83D\uDE20 " + " (" + monshGrowth + "%).\n";
        }
        if (monshGrowth > -5 && monshGrowth < 0) {
            return "*малое падение* \uD83D\uDE12 " + " (" + monshGrowth + "%).\n";
        }
        if (monshGrowth < 5 && monshGrowth > 0) {
            return "*малый рост* \uD83D\uDE0C " + " (+" + monshGrowth + "%).\n";
        }
        if (monshGrowth < 15 && monshGrowth > 5) {
            return "*умеренный рост* \uD83D\uDE0A " + " (+" + monshGrowth + "%).\n";
        }
        if (monshGrowth > 15) {
            return "*значительный рост* \uD83D\uDCB0 " + " (+" + monshGrowth + "%).\n";
        }
        if (monshGrowth == 0) {
            return "*не изменился в стоимости* (0%)\n";
        }

        return null;
    }

    public String yearGrowth(double yearGrowth) {
        if (yearGrowth < -20) {
            return "*значительное потерял в стоимости* \uD83D\uDE2D " + " (" + yearGrowth + "%).\n";
        }
        if (yearGrowth > -20 && yearGrowth < -7) {
            return "*умеренно падал* \uD83D\uDE20 " + " (" + yearGrowth + "%).\n";
        }
        if (yearGrowth > -7 && yearGrowth < 0) {
            return "*незначительно падал* \uD83D\uDE12 " + " (" + yearGrowth + "%).\n";
        }
        if (yearGrowth < 7 && yearGrowth > 0) {
            return "*незначительно рос* \uD83D\uDE0C " + " (+" + yearGrowth + "%).\n";
        }
        if (yearGrowth < 20 && yearGrowth > 7) {
            return "*умеренно рос* \uD83D\uDE0A " + " (+" + yearGrowth + "%).\n";
        }
        if (yearGrowth > 20) {
            return "*значительно рос* \uD83D\uDCB0 " + " (+" + yearGrowth + "%).\n";
        }
        if (yearGrowth == 0) {
            return "*не изменился в стоимости* (0%)\n";
        }

        return null;
    }

    public String getSectorCompany(String sector) {
        if (sector == null) {
            return "";
        } else {
            return "*Сектора работы данной компании*: _" + sector + "_\n\n";
        }
    }

    public String getSectorIndustry(String industry) {
        if (industry == null) {
            return "";
        } else {
            return "*Отрасль работы данной компании*: _" + industry + "_\n\n";
        }
    }

    public String getSectorCompanyDescription(String description) {
        if (description == null) {
            return "";
        } else {
            return description + "\n\n";
        }
    }

    @Override
    public List<String> getButtons() {
        return null;
    }
}
