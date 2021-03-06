package com.mabinogifanmade.squiretracker.activitiesfragments


import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_squire_details.*

class SquireDetailsFragment : BaseFragment() {
    val args: SquireDetailsFragmentArgs by navArgs()
    lateinit var squire: Squire
    lateinit var squireProgressPreview: HashMap<Int, Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squire_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            squire = args.squire
            listener?.updateTitles(getString(R.string.squire_details,squire.squireName))
            squireImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),squire.imageSquire))
            squireName.setText(squire.squireName)
            squireProgressPreview = UserUtils.getCurrentCharPlayer(requireContext()).squireProgress
            val progress: Int = UserUtils.getCurrentCharProgressForSquire(requireContext(), squire.id)
            setSquireText(progress)
            next.setOnClickListener(View.OnClickListener {
                setSquireText(squireProgressPreview.get(squire.id)!! + 1)
            })
            previous.setOnClickListener(View.OnClickListener {
                setSquireText(squireProgressPreview.get(squire.id)!! - 1)
            })
            nextAccept.setOnClickListener(View.OnClickListener {
                val user: UserGeneral? = ShrdPrfsUtils.getUserData(requireContext())
                user?.getCurrentCharacter()?.setSquireProgress(
                    squire, squireProgressPreview.get(squire.id)!! + 1
                )
                ShrdPrfsUtils.saveUserData(requireContext(), user!!)
                YoYo.with(Techniques.FadeInRight)
                    .duration(700)
                    .playOn(sequenceRelative);
                setSquireText(
                    user.getCurrentCharacter().squireProgress.get(squire.id)!!
                )
            })
            setNumber.setOnClickListener {
                showDialogInput()
            }
            specialConvo.setOnClickListener {
                val action =
                    SquireDetailsFragmentDirections.actionSquireDetailsFragmentToSpecialConvoDetails(
                        squire
                    )
                Navigation.findNavController(it).navigate(action)
            }
            sequenceConvo.setOnClickListener {
                val action =
                    SquireDetailsFragmentDirections.actionSquireDetailsFragmentToConvoDetails(
                        squire
                    )
                Navigation.findNavController(it).navigate(action)
            }
            findSequence.setOnClickListener {
                val action = when (squire.hasHint) {
                    true -> SquireDetailsFragmentDirections.actionSquireDetailsFragmentToFindSeqHintFragment(
                        squire
                    )
                    else -> SquireDetailsFragmentDirections.actionSquireDetailsFragmentToFindSeqFragment(
                        squire
                    )
                }
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun setSquireText(progress: Int) {
        if (context != null) {
            val index: Int = ConversationUtils.getNumberInSequence(progress, squire.sequenceConvo.length)
            sequenceText.text = requireContext().getString(
                R.string.number_sequence,
                (index + 1),
                ConversationUtils.translateCurrentAbv(squire, false, progress)
            )
            squireProgressPreview.put(squire.id, index)
            if (squire.hasHint) {
                hintText.visibility = View.VISIBLE
                hintText.text = requireContext().getString(
                    R.string.hint,
                    ConversationUtils.translateCurrentAbv(squire, true, progress)
                )
            } else {
                hintText.visibility = View.GONE
            }
        }
    }

    fun showDialogInput() {
        if (context != null && activity != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_input, null)
            builder.setView(dialogView)
            val dialog: AlertDialog = builder.create()
            dialog.setTitle(R.string.set_progress_number)
            val editText: TextInputEditText = dialogView.findViewById(R.id.inputEdit)
            val textLayout: TextInputLayout = dialogView.findViewById(R.id.inputLayout)
            textLayout.setHint(getString(R.string.squire_name_progress, squire.squireName))
            editText.inputType= InputType.TYPE_CLASS_NUMBER
            editText.setText((UserUtils.getCurrentCharProgressForSquire(requireContext(),squire.id)+1).toString())
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, requireContext().getText(R.string.submit_button),
                DialogInterface.OnClickListener { _, _ ->
                })
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, requireContext().getText(R.string.cancel_button),
                DialogInterface.OnClickListener { _, _ ->
                    dialog.dismiss()
                })
            dialog.show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val input:Int? = editText.text.toString().toIntOrNull()
                val isValid = when(input!=null){
                    true ->  input >= 1
                    else -> false
                }
                textLayout.error = if (isValid) null else getString(R.string.progress_error_msg)
                if (isValid && UserUtils.setCurrentCharSquireProgress(requireContext(),squire,
                        ConversationUtils.getNumberInSequenceWithOffset(input!!-1,squire.sequenceConvo.length))){
                    setSquireText(ConversationUtils.getNumberInSequenceWithOffset(input-1,squire.sequenceConvo.length))
                    dialog.dismiss()
                }
            }
        }
    }
}
