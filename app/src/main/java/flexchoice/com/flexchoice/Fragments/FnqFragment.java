package flexchoice.com.flexchoice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import flexchoice.com.flexchoice.Activity.Company;
import flexchoice.com.flexchoice.Activity.Product;
import flexchoice.com.flexchoice.Adapters.ProductAdapter;
import flexchoice.com.flexchoice.R;

public class FnqFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view= inflater.inflate(R.layout.fragment_fnq, container, false);

        //expandable recyclerview

        RecyclerView recyclerView = view.findViewById(R.id.fnq);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Company> companies = new ArrayList<>();

        ArrayList<Product> googleProduct = new ArrayList<>();

        googleProduct.add(new Product("Yes, of course! With our smart Rent2Own feature, you can rent to try our products and later, decide to purchase and own them. This is applicable on all RentoMojo furniture, appliances and bikes on a minimum rental tenure of 12 months. If you wish to purchase any of our products or know more about Rent2Own, please get in touch with our support team on 1860-121-1233 (10 AM - 8 PM). "));

        Company google = new Company("Can I purchase the furniture?   ",googleProduct);
        companies.add(google);

        googleProduct.add(new Product("On booking confirmation, a fixed minimal amount is collected as a security deposit." +
                " However, this is fully refundable to the customers if the products are returned in a good condition. "));

        Company google1 = new Company("Why do I have to a pay deposit for the furniture?   ",googleProduct);
        companies.add(google1);

        googleProduct.add(new Product("Not at all. That's right! we deliver, pick up and install the products absolutely free of cost and as per your schedule. Our delivery staff will discuss with you, placement of the products as per your preference and install accordingly. In case delivery has to be made at a location without an elevator," +
                " there will be a nominal labour fee charged to convey the products which will be collected in cash at the time of delivery; the amount will vary basis order size and floor level.  "));
        Company google2 = new Company("Do I have to pay for delivery and installation?   ",googleProduct);
        companies.add(google2);


        googleProduct.add(new Product("After completion of payment, please upload the necessary documents. Once received, your order will be delivered within 72 hours. " +
                "We will reach out, if any other document is needed.  "));

        Company google3 = new Company("What is the delivery time of the order?  ",googleProduct);
        companies.add(google3);

        ArrayList<Product>microsoftProduct = new ArrayList<>();
        microsoftProduct.add(new Product("Damage which makes the furniture functionally unusable is chargeable. The amount will be decided by RentoMojo on a case to case basis." +
                " Normal wear and tear which does not cause functional damage to the products due to usage is absolutely free . "));


        Company microsoft = new Company("What if I damage the furniture?", microsoftProduct);
        companies.add(microsoft);


        microsoftProduct.add(new Product("We will pick up the products on the completion of your rental tenure, and upon successful Quality Check," +
                " we will immediately initiate the refund of your security deposit to your registered bank account in 5 working days. No interest is paid on the deposit amount. "));


        Company microsoft1 = new Company("When do I get my deposit back?", microsoftProduct);
        companies.add(microsoft1);

        microsoftProduct.add(new Product("In an unlikely situation of damaged furniture arriving at your doorstep but if it is damaged," +
                " we won't ask any questions and take the furniture back with us.  "));


        Company microsoft2 = new Company("What is the return policy?", microsoftProduct);
        companies.add(microsoft2);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }
}
