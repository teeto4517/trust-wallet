# Wallet Creation Test Cases

## Functional Test Cases

### Onboarding Screen
- Verify the onboarding screen loads successfully when the app is launched.
- Confirm that the 'Create new wallet' button is visible and can be tapped to navigate to the passcode creation screen.
- Check that the Terms of Service and Privacy Policy links are present at the bottom and open the correct documents when tapped.

---

### Successful Wallet Creation

**Test Case: Verify successful wallet creation flow**

- **Precondition:**  
  - The app is installed and launched.  
  - User is on the onboarding screen.

- **Steps:**  
  1. Tap the **"Create new wallet"** button on the onboarding screen.  
  2. On the passcode creation screen, enter a valid 6-digit passcode using the numeric keypad.  
  3. When prompted, re-enter the same 6-digit passcode to confirm.  
  4. (If applicable) Accept or skip any backup or recovery phrase prompts.  
  5. Complete any additional onboarding steps required by the app (e.g., accept terms, enable notifications).  
  6. Wait for the wallet creation process to complete.

- **Expected Result:**  
  - The app accepts both passcode entries and any required confirmations.
  - The user is shown a success message or confirmation screen indicating the wallet has been created.
  - The app navigates to the wallet main/dashboard screen, displaying wallet balance and available features.
  - The user does not return to the passcode or onboarding screens unless the app is reinstalled or reset.
  - No error messages are displayed during the process.

---


### Passcode Creation Screen

#### Passcode Entry

**1. Verify user can enter a 6-digit passcode using the numeric keypad**
- **Precondition:** User is on the passcode creation screen.
- **Steps:**
  1. Tap digits 1 through 6 on the numeric keypad in sequence.
- **Expected Result:**
  - Each tap fills the next available passcode box.
  - All 6 boxes are filled after entering 6 digits.
  - The system accepts the input and may proceed to the next step (e.g., confirmation).

**2. Verify passcode dots/squares fill as digits are entered**
- **Precondition:** User is on the passcode creation screen with all boxes empty.
- **Steps:**
  1. Tap a digit and observe the first box.
  2. Tap another digit and observe the second box.
  3. Continue for all 6 digits.
- **Expected Result:**
  - Each box fills in order as a digit is entered.
  - Visual feedback is immediate and clear for each entry.

**3. Verify passcode is masked/hidden for security**
- **Precondition:** User is on the passcode creation screen.
- **Steps:**
  1. Enter any digit on the keypad.
  2. Observe the corresponding passcode box.
- **Expected Result:**
  - Entered digit is not shown as plain text.
  - The box displays a dot, asterisk, or similar masking character.
  - No actual digits are visible on the screen.

**4. Verify user can delete entered digits using the X button**
- **Precondition:** At least one digit has been entered on the passcode creation screen.
- **Steps:**
  1. Tap the X (delete) button once.
  2. Observe the passcode boxes.
  3. Tap the X button again to delete another digit.
- **Expected Result:**
  - Each tap on the X button removes the last entered digit.
  - The corresponding box is cleared and ready for new input.
  - Remaining digits are preserved.

---

#### Passcode Validation

**1. Verify system accepts valid 6-digit passcode**
- **Precondition:** User is on the passcode creation screen.
- **Steps:**
  1. Enter a valid 6-digit passcode (e.g., 123456).
  2. Complete the entry.
- **Expected Result:**
  - The system accepts the 6-digit passcode.
  - User is allowed to proceed to the next step (e.g., passcode confirmation or wallet setup).
  - No error messages are displayed.

**2. Verify system rejects passcode with fewer than 6 digits**
- **Precondition:** User is on the passcode creation screen.
- **Steps:**
  1. Enter fewer than 6 digits (e.g., 123).
  2. Attempt to proceed or wait for system response.
- **Expected Result:**
  - The system does not accept the incomplete passcode.
  - User remains on the current screen.
  - A visual indication or message may appear, prompting the user to enter all 6 digits.

**3. Verify passcode confirmation step (re-enter to confirm)**
- **Precondition:** User has successfully entered a valid 6-digit passcode and is prompted to confirm it.
- **Steps:**
  1. Enter the same 6-digit passcode again in the confirmation field.
  2. Complete the entry.
- **Expected Result:**
  - The system compares the confirmation passcode with the original.
  - If they match, the user proceeds to the next step (e.g., wallet creation success).
  - No error messages are displayed.

**4. Verify error handling for mismatched passcode confirmation**
- **Precondition:** User is on the passcode confirmation screen after entering the initial passcode.
- **Steps:**
  1. Enter a different 6-digit passcode in the confirmation field (e.g., 654321).
  2. Complete the entry.
- **Expected Result:**
  - The system detects the mismatch between the original and confirmation passcodes.
  - An error message is displayed, informing the user of the mismatch.
  - The confirmation fields are cleared, and the user is prompted to try again.

---

### Navigation

**1. Verify back arrow returns to previous screen**
- **Precondition:** User is on the passcode creation screen.
- **Steps:**
  1. Tap the back arrow in the top left corner.
- **Expected Result:**
  - The app navigates to the previous screen (onboarding or prior step).
  - No data is lost on the previous screen.

**2. Verify proper flow after successful passcode creation**
- **Precondition:** User has successfully created and confirmed a valid 6-digit passcode.
- **Steps:**
  1. Complete the passcode creation and confirmation steps.
  2. Observe the next screen or process.
- **Expected Result:**
  - The app proceeds to the wallet main screen or the next setup step.
  - The user does not return to the passcode creation screen unless starting on a freshly install app.
  - The flow is logical and expected.

**3. Verify app state preservation during screen transitions**
- **Precondition:** User is on the passcode creation screen and has entered some digits.
- **Steps:**
  1. Enter 3 digits of the passcode.
  2. Press the home button to background the app.
  3. Return to the app within 30 seconds.
- **Expected Result:**
  - The entered digits are preserved.
  - The user resumes from the same state without losing progress.
  - The app does not reset or clear the passcode entry.

---


## UI/UX Test Cases

### Onboarding Screen
- Branding and graphics should be displayed clearly, with all images and icons sharp, not distorted, and properly aligned.
- The 'Create new wallet' button should be blue and prominent, while the 'I already have a wallet' button should be gray; both should have rounded corners.
- All text should be left-aligned, readable, and use a consistent font.
- The Terms of Service and Privacy Policy links should be styled as links (e.g., blue and underlined or otherwise visually distinct).

### Passcode Creation Screen
- The six passcode boxes should be evenly spaced, horizontally centered, and visually balanced.
- The numeric keypad should be arranged in a 3x4 grid, and each button should provide immediate visual feedback when tapped.
- The fingerprint (biometric) and X (delete) icons should be present, properly sized, and aligned with the keypad.
- Instructional text below the passcode boxes should be present, readable, and not truncated. 