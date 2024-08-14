package com.example.unsplash.Fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.content
import com.example.unsplash.databinding.FragmentExcelSheetBinding
import com.example.unsplash.recyclerViewAdaptar
import com.example.unsplash.studentDetails
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.IOException
import java.io.InputStream


class excelSheet : Fragment() {
    private lateinit var binding: FragmentExcelSheetBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var Rvadaptar: recyclerViewAdaptar
    private lateinit var dataList: ArrayList<studentDetails>
    private lateinit var rollNumberToPhoneNumber: MutableMap<String?, String?>
    private val PICK_FILE_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExcelSheetBinding.inflate(layoutInflater)
        //Giving all the values to previous initialization
        rollNumberToPhoneNumber = mutableMapOf<String?, String?>()


        binding.openFile.setOnClickListener {
            openFilePicker()
        }
        Rvadaptar = recyclerViewAdaptar(content.getList(rollNumberToPhoneNumber), requireContext())
        recyclerView = binding.rvDataRolllPhone

        //setting up recyclerview


        recyclerView.adapter = Rvadaptar
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root

    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                rollNumberToPhoneNumber = handleFile(uri)
                // Do something with the data stored in rollNumberToPhoneNumber
            }
        }
    }

    private fun handleFile(uri: Uri): MutableMap<String?, String?> {
        return try {
            requireContext().contentResolver.openInputStream(uri)?.use { inputStream ->
                readExcelFile(inputStream)
            } ?: mutableMapOf()
        } catch (e: IOException) {
            e.printStackTrace()
            mutableMapOf()
        }
    }

    private fun readExcelFile(inputStream: InputStream): MutableMap<String?, String?> {
        val rollNumberToPhoneNumber = mutableMapOf<String?, String?>()
        val workbook = WorkbookFactory.create(inputStream)
        val sheet = workbook.getSheetAt(0)

        for (row in sheet) {
            if (row.rowNum == 0) continue // Skip header row

            val rollNumberCell = row.getCell(0)
            val phoneNumberCell = row.getCell(3)

            if (rollNumberCell.cellType == CellType.STRING && phoneNumberCell.cellType == CellType.STRING) {
                val rollNumber = rollNumberCell.stringCellValue
                val phoneNumber = phoneNumberCell.stringCellValue

                rollNumberToPhoneNumber[rollNumber] = phoneNumber
            }
        }

        workbook.close()
        return rollNumberToPhoneNumber
    }


}