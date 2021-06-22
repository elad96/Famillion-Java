import il.ac.hit.javacourse.finalproject.model.FamillionModel;
import il.ac.hit.javacourse.finalproject.model.IFamillionModel;
import il.ac.hit.javacourse.finalproject.view.FamillionView;
import il.ac.hit.javacourse.finalproject.view.IFamillionView;
import il.ac.hit.javacourse.finalproject.viewmodel.FamillionViewModel;
import il.ac.hit.javacourse.finalproject.viewmodel.IFammillionViewModel;

public class Program {
    /**
     * The programs main functions - use to execute Famillion
     * @param args
     */
    public static void main(String[] args) {

        IFamillionView view = new FamillionView();
        IFammillionViewModel viewmodel = new FamillionViewModel();
        IFamillionModel model = new FamillionModel();
        viewmodel.setModel(model);
        viewmodel.setView(view);
        view.setViewModel(viewmodel);

    }
}
