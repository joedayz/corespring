package rewards.internal.account;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by josediaz on 4/14/14.
 */
public class Account {


    private String number;

    private String name;

    private Set<Beneficiary> beneficiaries = new HashSet<Beneficiary>();
}
