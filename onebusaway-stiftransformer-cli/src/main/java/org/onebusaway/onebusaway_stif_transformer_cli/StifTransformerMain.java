package org.onebusaway.onebusaway_stif_transformer_cli;

import org.onebusaway.onebusaway_stif_transformer.StifTransformerSuite;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformSpecificationException;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StifTransformerMain {

    private static Logger _log = LoggerFactory.getLogger(StifTransformerMain.class);

    private static CommandLineParser _parser = new PosixParser();
    private Options _options = new Options();


    public static void main(String[] args) throws IOException{
        StifTransformerMain s = new StifTransformerMain();
        s.run(args);
    }

    public StifTransformerMain(){
        //buildOptions(_options);
    }

    public void run(String[] args) throws IOException {

        if (needsHelp(args)) {
            printHelp();
            System.exit(0);
        }

        try {
            CommandLine cli = _parser.parse(_options, args, true);
            runApplication(cli, args);
        } catch (MissingOptionException ex) {
            System.err.println("Missing argument: " + ex.getMessage());
            printHelp();
            System.exit(-2);
        } catch (MissingArgumentException ex) {
            System.err.println("Missing argument: " + ex.getMessage());
            printHelp();
            System.exit(-2);
        } catch (UnrecognizedOptionException ex) {
            System.err.println("Unknown argument: " + ex.getMessage());
            printHelp();
            System.exit(-2);
        } catch (AlreadySelectedException ex) {
            System.err.println("Argument already selected: " + ex.getMessage());
            printHelp();
            System.exit(-2);
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
            printHelp();
            System.exit(-2);
        } catch (TransformSpecificationException ex) {
            System.err.println("error with transform line: " + ex.getLine());
            System.err.println(ex.getMessage());
            System.exit(-1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    protected void runApplication(CommandLine cli, String[] originalArgs)
            throws Exception {

        String[] args = cli.getArgs();

        if (args.length < 3) {
            printHelp();
            System.exit(-1);
        }



        String[] paths = new String[args.length - 3];
        for (int i = 0; i < args.length - 3; ++i) {
            paths[i] = args[i];
            _log.info("input path: " + args[i]);
        }
        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(paths);
        _log.info("transform: " + args[args.length - 3]);
        transformerSuite.setTranform(args[args.length - 3]);
        _log.info("output path: " + args[args.length - 2]);
        transformerSuite.setOutputPath(args[args.length - 2]);
        _log.info("Restructuring file archetecture: " + args[args.length - 1]);
        //transformerSuite.setOutputFormat(args[args.length - 1]);
        transformerSuite.run();
    }

    private Option[] getOptionsInCommandLineOrder(CommandLine cli,
                                                  String[] originalArgs) {

        Option[] options = cli.getOptions();
        List<Ordered<Option>> orderedOptions = new ArrayList<Ordered<Option>>();

        for (Option option : options) {

            String argName = option.getOpt();
            int optionPosition = originalArgs.length;

            for (int i = 0; i < originalArgs.length; i++) {
                if (originalArgs[i].endsWith(argName)) {
                    optionPosition = i;
                    break;
                }
            }

            orderedOptions.add(new Ordered<Option>(option, optionPosition));
        }

        Collections.sort(orderedOptions);
        options = new Option[options.length];

        for (int i = 0; i < options.length; i++)
            options[i] = orderedOptions.get(i).getObject();

        return options;
    }


    protected void printHelp() throws IOException {
        printHelp(new PrintWriter(System.err, true), _options);
    }

    private boolean needsHelp(String[] args) {
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help") || arg.equals("-help"))
                return true;
        }
        return false;
    }

    protected void printHelp(PrintWriter out, Options options) throws IOException {

        InputStream is = getClass().getResourceAsStream("usage.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while ((line = reader.readLine()) != null) {
            System.err.println(line);
        }

        reader.close();
    }


    private static class Ordered<T> implements Comparable<Ordered<T>> {

        private T _object;

        private int _order;

        public Ordered(T object, int order) {
            _object = object;
            _order = order;
        }

        public T getObject() {
            return _object;
        }

        @Override
        public int compareTo(Ordered<T> o) {
            return _order - o._order;
        }
    }

}
