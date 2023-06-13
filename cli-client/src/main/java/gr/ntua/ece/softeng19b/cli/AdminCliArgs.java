package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;

import static picocli.CommandLine.*;


public class AdminCliArgs extends BasicCliArgs{
    public enum Datarange {
        ActualTotalLoad,
        AggregatedGenerationPerType,
        DayAheadTotalLoadForecast
    }
    @ArgGroup(exclusive = true, multiplicity = "1")
    protected Exclusive exclusive;

    static class Exclusive {

        @ArgGroup(exclusive = false)
        protected NewModuser newmoduser;

        static class NewModuser {
            @ArgGroup(exclusive = true, multiplicity = "1")
            protected NewModuser1 newmoduser1;

            static class NewModuser1 {
                @Option(names = "--newuser", required = true) protected String newuser;
                @Option(names = "--moduser", required = true) protected String moduser;
            }
            @Option(names = "--passw", required = true) protected String password;
            @Option(names = "--email", required = true) protected String email;
            @Option(names = "--quota", required = true) protected int quota;
        }

        @ArgGroup(exclusive = false)
        Userstatus userstatus = new Userstatus();
        static class Userstatus {
            @Option(names = "--userstatus", required = true)
            protected String userstatus;

        }

        @ArgGroup(exclusive = false)
        Newdata newdata = new Newdata();
        static class Newdata {
            @Option(names = "--newdata", required = true, description = "where options is one of the following: ${COMPLETION-CANDIDATES}.") protected Datarange newdata;
            @Option(names = "--source", required = true) protected String source;
        }
    }
}
