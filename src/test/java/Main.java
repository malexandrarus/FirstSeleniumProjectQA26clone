
public class Main {

    public static void main(String[] args) {
        LoginTest login = new LoginTest();
        RegisterTest register = new RegisterTest();
        WishListTest wishListTest = new WishListTest();

//        login.loginWithValidCredentialsTest();
//        login.loginWithInvalidEmail();
//        login.loginWithInvalidPasswordTest();
//        login.loginWithoutCredentialsTest();
//        login.forgotPasswordTest();
//        login.loginWithValidCredentialsFollowedByEnterKeyTest();

//        register.validRegisterTest();
//        register.registerWithInvalidEmailTest();
//        register.registerTheMandatoryFieldsWithoutFillingAnyDataAndClickOnRegisterTest();
//        register.registerWithMandatoryFieldsByFillingWithBlankSpacesAndClickOnRegisterTest();
//        register.registerWithMinValueForPassword();
//        register.registerWithMaxValueForPassword();

        wishListTest.addProductToWishListTest();
//        wishListTest.removeProductFromWishlistAfterItWasAddedTest();
//        wishListTest.addProductFromWishlistToCartTest();
//        wishListTest.shareWishlistTest();
//        wishListTest.modifyQuantityForAProductThatWasAddedToWishlist();

    }

}
