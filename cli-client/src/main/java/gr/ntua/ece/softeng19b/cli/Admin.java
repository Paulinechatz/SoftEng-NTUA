package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import picocli.CommandLine;
import gr.ntua.ece.softeng19b.data.model.User;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
        name="Admin"
)
public class Admin extends AdminCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if (exclusive.newdata.newdata != null) {
                String dataset;
                if (exclusive.newdata.newdata == Datarange.ActualTotalLoad) {
                    dataset = "ActualTotalLoad";
                }
                else if (exclusive.newdata.newdata == Datarange.AggregatedGenerationPerType) {
                    dataset = "AggregatedGenarationPerType";
                }
                else {
                    dataset = "DayAheadTotalLoadForecast";
                }
                Path path = Paths.get(exclusive.newdata.source);
//            System.out.println(path.toAbsolutePath());
                new RestAPI().importFile(dataset, path);
                return 0;
            } else if (exclusive.userstatus.userstatus != null) {
                List<User> user =new RestAPI().getUser(exclusive.userstatus.userstatus);
                for(int i=0; i<user.size(); i++){
                    System.out.println("Username : " +user.get(i).getUsername());
                    System.out.println("Email : " +user.get(i).getEmail());
                    System.out.println("User requests per day quota : " +user.get(i).getRequestsPerDayQuota());
                }
                return 0;
            } else if (exclusive.newmoduser.newmoduser1.newuser != null) {
                User user = new RestAPI().addUser(exclusive.newmoduser.newmoduser1.newuser, exclusive.newmoduser.email, exclusive.newmoduser.password, exclusive.newmoduser.quota);
                System.out.println("Username : " +user.getUsername());
                System.out.println("Email : " +user.getEmail());
                System.out.println("User requests per day quota : " +user.getRequestsPerDayQuota());
                return 0;
            } else if (exclusive.newmoduser.newmoduser1.moduser != null) {
                User user = new User(exclusive.newmoduser.newmoduser1.moduser, exclusive.newmoduser.email, exclusive.newmoduser.quota);
                User user1 = new RestAPI().updateUser(user);
                System.out.println("Username : " +user1.getUsername());
                System.out.println("Email : " +user1.getEmail());
                System.out.println("User requests per day quota : " +user1.getRequestsPerDayQuota());
                return 0;
            } else
                return 0;
        }
         catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }

}
