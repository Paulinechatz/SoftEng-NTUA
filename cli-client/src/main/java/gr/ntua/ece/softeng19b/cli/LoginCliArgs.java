package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;

import static picocli.CommandLine.*;

@Command
public class LoginCliArgs extends BasicCliArgs {


    @Option(
            names = "--username",
            required = true,
            description = "fill in your username(only letter and numbers are accepted)."
    )
    protected String username;


    @Option(
            names = "--passw",
            required = true,
            interactive = true,
            description = "to not use spaces"
    )
    protected String password;

}