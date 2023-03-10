package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_EVENT_TAG_CARNIVAL = "Carnival";
    public static final String VALID_EVENT_TAG_SPORTS_DAY = "20th Sports Day @ Sports Hub";
    public static final String VALID_EVENT_TAG_WEDDING_DINNER = "Wedding Dinner";
    public static final String VALID_EVENT_INDEX_TAG_CARNIVAL = "2";
    public static final String VALID_EVENT_INDEX_TAG_SPORTS_DAY = "3";
    public static final String VALID_EVENT_INDEX_TAG_WEDDING_DINNER = "1";


    public static final String VALID_EVENT_NAME_CARNIVAL = "Carnival";
    public static final String VALID_EVENT_NAME_SPORTS_DAY = "20th Sports Day @ Sports Hub";
    public static final String VALID_START_DATE_TIME_CARNIVAL = "02-02-2024 09:00";
    public static final String VALID_START_DATE_TIME_SPORTS_DAY = "02-03-2024 08:30";
    public static final String VALID_END_DATE_TIME_CARNIVAL = "10-02-2024 22:00";
    public static final String VALID_END_DATE_TIME_SPORTS_DAY = "02-03-2024 18:30";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String EVENT_TAG_DESC_CARNIVAL = " " + PREFIX_EVENT_TAG + VALID_EVENT_INDEX_TAG_CARNIVAL;
    public static final String EVENT_TAG_DESC_SPORTS_DAY = " " + PREFIX_EVENT_TAG + VALID_EVENT_INDEX_TAG_SPORTS_DAY;
    public static final String EVENT_TAG_DESC_WEDDING_DINNER = " " + PREFIX_EVENT_TAG
            + VALID_EVENT_INDEX_TAG_WEDDING_DINNER;

    public static final String EVENT_NAME_DESC_CARNIVAL = " " + PREFIX_EVENT_NAME + VALID_EVENT_NAME_CARNIVAL;
    public static final String EVENT_NAME_DESC_SPORTS_DAY = " " + PREFIX_EVENT_NAME + VALID_EVENT_NAME_SPORTS_DAY;
    public static final String START_DATE_TIME_DESC_CARNIVAL = " " + PREFIX_START_DATE_TIME
            + VALID_START_DATE_TIME_CARNIVAL;
    public static final String START_DATE_TIME_DESC_SPORTS_DAY = " " + PREFIX_START_DATE_TIME
            + VALID_START_DATE_TIME_SPORTS_DAY;
    public static final String END_DATE_TIME_DESC_CARNIVAL = " " + PREFIX_END_DATE_TIME + VALID_END_DATE_TIME_CARNIVAL;
    public static final String END_DATE_TIME_DESC_SPORTS_DAY = " " + PREFIX_END_DATE_TIME
            + VALID_END_DATE_TIME_SPORTS_DAY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_EVENT_TAG + "hubby*"; // tags should be numeric

    // Event names not allowed to start with punctuations
    public static final String INVALID_EVENT_NAME_DESC = " " + PREFIX_EVENT_NAME + "'20TH' Company anniversary";
    // Invalid date time format
    public static final String INVALID_START_DATE_TIME_DESC = " " + PREFIX_START_DATE_TIME + "02-02-202 12:00";
    // Value used is out of range
    public static final String INVALID_END_DATE_TIME_DESC = " " + PREFIX_END_DATE_TIME + "40-40-2024 12:99";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags().withEventIndexTags(VALID_EVENT_INDEX_TAG_CARNIVAL).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags().withEventIndexTags(VALID_EVENT_INDEX_TAG_SPORTS_DAY, VALID_EVENT_INDEX_TAG_CARNIVAL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitEventName = event.getName().toString().split("\\s+");
        model.updateFilteredEventList(new EventNameContainsKeywordsPredicate(Arrays.asList(splitEventName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
