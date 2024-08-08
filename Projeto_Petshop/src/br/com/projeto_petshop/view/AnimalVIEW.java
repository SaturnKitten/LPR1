
package br.com.projeto_petshop.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_petshop.dto.TutorDTO;
import br.com.projeto_petshop.ctr.TutorCTR;
import br.com.projeto_petshop.dto.AnimalDTO;
import br.com.projeto_petshop.ctr.AnimalCTR;

public class AnimalVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form AnimalVIEW
     */
    
    TutorDTO tutorDTO = new TutorDTO();//Cria um objeto tutorDTO
    TutorCTR tutorCTR = new TutorCTR();//Cria um objeto tutorCTR
    AnimalDTO animalDTO = new AnimalDTO();//Cria um objeto animalDTO
    AnimalCTR animalCTR = new AnimalCTR();//Cria um objeto animalCTR
    
    int gravar_alterar;//Variável usada para saber se está alterando ou incluindo
    
    ResultSet rs;//Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_tutor;//Variável para guardar o modelo da tabela
    DefaultTableModel modelo_jtl_consultar_animal;//Variável para guardar o modelo da tabela
    
    //Método para centralizar o internalFrame
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
    }//Fecha método setPosicao
    
    //Método utilizado para gravar os dados do animal
    private void gravar(){
        try{
            animalDTO.setNome_animal(nome_animal.getText());
            animalDTO.setEspecie_animal(especie_animal.getSelectedItem().toString());
            animalDTO.setSexo_animal(sexo_animal.getSelectedItem().toString());
            animalDTO.setRaca_animal(raca_animal.getText());
            animalDTO.setPorte_animal(porte_animal.getSelectedItem().toString());
            animalDTO.setCor_animal(cor_animal.getText());
            animalDTO.setPelagem_animal(pelagem_animal.getSelectedItem().toString());
            tutorDTO.setId_tutor(Integer.parseInt(String.valueOf(
                jtl_consultar_tutor.getValueAt(
                jtl_consultar_tutor.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null, animalCTR.inserirAnimal(animalDTO, tutorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao gravar" + e.getMessage());
        }
    }//Fecha método gravar
    
    //Método utilizado para alterar os dados do animal
    private void alterar(){
        try{
            animalDTO.setNome_animal(nome_animal.getText());
            animalDTO.setEspecie_animal(especie_animal.getSelectedItem().toString());
            animalDTO.setSexo_animal(sexo_animal.getSelectedItem().toString());
            animalDTO.setRaca_animal(raca_animal.getText());
            animalDTO.setPorte_animal(porte_animal.getSelectedItem().toString());
            animalDTO.setCor_animal(cor_animal.getText());
            animalDTO.setPelagem_animal(pelagem_animal.getSelectedItem().toString());
            tutorDTO.setId_tutor(Integer.parseInt(String.valueOf(
                jtl_consultar_tutor.getValueAt(
                jtl_consultar_tutor.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null, animalCTR.alterarAnimal(animalDTO, tutorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao alterar" + e.getMessage());
        }
    }//Fecha método alterar
    
    //Método utilizado para excluir os dados do tutor
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o animal?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, animalCTR.excluirAnimal(animalDTO));
        };
    }//Fecha método excluir
    
    //Método utilizado para liberar/bloquear os campos da tela
    private void liberaCampos(boolean a){
        nome_animal.setEnabled(a);
        especie_animal.setEnabled(a);
        sexo_animal.setEnabled(a);
        raca_animal.setEnabled(a);
        porte_animal.setEnabled(a);
        cor_animal.setEnabled(a);
        pelagem_animal.setEnabled(a);
        pesquisa_nome_tutor.setEnabled(a);
        btn_pesquisar_tutor.setEnabled(a);
        jtl_consultar_tutor.setEnabled(a);
    }//Fecha método liberaCampos
    
    //Método utilizado para liberar os botões da tela
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btn_novo.setEnabled(a);
        btn_salvar.setEnabled(b);
        btn_cancelar.setEnabled(c);
        btn_excluir.setEnabled(d);
        btn_sair.setEnabled(e);
    }//Fecha método liberaBotoes
    
    //Método utilizado para limpar os campos da tela
    private void limpaCampos(){
        nome_animal.setText("");
        raca_animal.setText("");
        cor_animal.setText("");
        pesquisa_nome_tutor.setText("");
        modelo_jtl_consultar_tutor.setNumRows(0);
    }//Fecha método limpaCampos
    
    //Método utilizado para preencher/construir a jtable
    private void preencheTabelaAnimal(String nome_animal){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_animal.setNumRows(0);
            //Enquanto tiver linhas - faça
            animalDTO.setNome_animal(nome_animal);
            rs = animalCTR.consultarAnimal(animalDTO, 1); //i = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_animal.addRow(new Object[]{
                   rs.getString("id_animal"),
                   rs.getString("nome_animal"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            tutorCTR.CloseDB();
        }
    }//Fecha método preencheTabela
    
    //Método utilizado para preencher os campos da tela com valores do animal
    private void preencheCamposAnimal(int id_animal){
        try{
            animalDTO.setId_animal(id_animal);
            rs = animalCTR.consultarAnimal(animalDTO, 2);//2 = é a pesquisa no id na classe DAO
            if(rs.next()){
            limpaCampos();
                
            nome_animal.setText(rs.getString("nome_animal"));
            especie_animal.setSelectedItem(rs.getString("especie_animal"));
            sexo_animal.setSelectedItem(rs.getString("sexo_animal"));
            raca_animal.setText(rs.getString("raca_animal"));
            porte_animal.setSelectedItem(rs.getString("porte_animal"));
            cor_animal.setText(rs.getString("cor_animal"));
            pelagem_animal.setSelectedItem(rs.getString("pelagem_animal"));
                        
            //Colocando os dados do tutor
            modelo_jtl_consultar_tutor.setNumRows(0);
            modelo_jtl_consultar_tutor.addRow(new Object[]{rs.getInt("id_tutor"), rs.getString("nome_tutor"),});
            jtl_consultar_tutor.setRowSelectionInterval(0, 0);
            
            gravar_alterar = 2;
            liberaCampos(true);
            }//Fecha if
        }//Fecha try
        catch(Exception erTab){
            System.out.println("Erro SQL" + erTab);
        }
        finally{
            animalCTR.CloseDB();
        }
    }//Fecha método preencheCamposAnimal
    
    //Método utilizado para preencher/construir a Jtable
    private void preencheTabelaTutor(String nome_tutor){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_tutor.setNumRows(0);
            //Enquanto tiver linhas - faça
            tutorDTO.setNome_tutor(nome_tutor);
            rs = tutorCTR.consultarTutor(tutorDTO, 1);//1 = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_tutor.addRow(new Object[]{
                    rs.getString("id_tutor"),
                    rs.getString("nome_tutor"),
                });
            }//Fecha while
        }//Fecha try
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            animalCTR.CloseDB();
        }
    }//Fecha método preencheTabelaTutor
    
    public AnimalVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_tutor = (DefaultTableModel) jtl_consultar_tutor.getModel();
        modelo_jtl_consultar_animal = (DefaultTableModel) jtl_consultar_animal.getModel();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nome_animal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        especie_animal = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        sexo_animal = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        raca_animal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        porte_animal = new javax.swing.JComboBox<>();
        cor_animal = new javax.swing.JTextField();
        pelagem_animal = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_tutor = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        pesquisa_nome_tutor = new javax.swing.JTextField();
        btn_pesquisar_tutor = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_animal = new javax.swing.JTable();
        pesquisa_nome_animal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_pesquisar_animal = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CADASTRO DE ANIMAIS");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nome:");

        jLabel3.setText("Espécie:");

        especie_animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GATO", "CACHORRO" }));

        jLabel4.setText("Sexo:");

        sexo_animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M" }));

        jLabel5.setText("Raça:");

        jLabel8.setText("Porte:");

        porte_animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRANDE", "MÉDIO", "PEQUENO", "MINI" }));

        pelagem_animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LONGA", "CURTA" }));

        jLabel10.setText("Pelagem:");

        jtl_consultar_tutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_tutor);
        if (jtl_consultar_tutor.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_tutor.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_tutor.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtl_consultar_tutor.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel11.setText("Tutor:");

        btn_pesquisar_tutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar_tutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_tutorActionPerformed(evt);
            }
        });

        jLabel12.setText("Cor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(especie_animal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel5)))
                                    .addComponent(porte_animal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cor_animal)
                                    .addComponent(raca_animal)))
                            .addComponent(nome_animal)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pesquisa_nome_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_pesquisar_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pelagem_animal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(sexo_animal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(261, 261, 261))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(178, 178, 178))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(especie_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(raca_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(porte_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cor_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pelagem_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(sexo_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesquisar_tutor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisa_nome_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/novo.png"))); // NOI18N
        btn_novo.setText("Novo");
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/salvar.png"))); // NOI18N
        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/cancelar.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/excluir.png"))); // NOI18N
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/sair.png"))); // NOI18N
        btn_sair.setText("Sair");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtl_consultar_animal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_animal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_animalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_animal);
        if (jtl_consultar_animal.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_animal.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_animal.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtl_consultar_animal.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("CONSULTA");

        jLabel7.setText("Nome:");

        btn_pesquisar_animal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar_animal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_animalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pesquisa_nome_animal, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisar_animal)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(145, 145, 145))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisa_nome_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(btn_pesquisar_animal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(390, 390, 390))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_novo)
                .addGap(15, 15, 15)
                .addComponent(btn_salvar)
                .addGap(15, 15, 15)
                .addComponent(btn_cancelar)
                .addGap(19, 19, 19)
                .addComponent(btn_excluir)
                .addGap(15, 15, 15)
                .addComponent(btn_sair)
                .addGap(140, 140, 140))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo)
                    .addComponent(btn_salvar)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_sair))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_animal.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_animal.setNumRows(0);

    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_pesquisar_animalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_animalActionPerformed
        preencheTabelaAnimal(pesquisa_nome_animal.getText());
    }//GEN-LAST:event_btn_pesquisar_animalActionPerformed

    private void btn_pesquisar_tutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_tutorActionPerformed
        preencheTabelaTutor(pesquisa_nome_tutor.getText());
    }//GEN-LAST:event_btn_pesquisar_tutorActionPerformed

    private void jtl_consultar_animalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_animalMouseClicked
        //Pega o id do produto selecionado e chama preencheCampos
        preencheCamposAnimal(Integer.parseInt(String.valueOf(
            jtl_consultar_animal.getValueAt(
            jtl_consultar_animal.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_animalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar_animal;
    private javax.swing.JButton btn_pesquisar_tutor;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JTextField cor_animal;
    private javax.swing.JComboBox<String> especie_animal;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_animal;
    private javax.swing.JTable jtl_consultar_tutor;
    private javax.swing.JTextField nome_animal;
    private javax.swing.JComboBox<String> pelagem_animal;
    private javax.swing.JTextField pesquisa_nome_animal;
    private javax.swing.JTextField pesquisa_nome_tutor;
    private javax.swing.JComboBox<String> porte_animal;
    private javax.swing.JTextField raca_animal;
    private javax.swing.JComboBox<String> sexo_animal;
    // End of variables declaration//GEN-END:variables
}
