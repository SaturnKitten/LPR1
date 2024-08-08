
package br.com.projeto_petshop.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_petshop.dto.TutorDTO;
import br.com.projeto_petshop.ctr.TutorCTR;

public class TutorVIEW extends javax.swing.JInternalFrame {

    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    TutorDTO tutorDTO = new TutorDTO(); //Cria um objeto tutorDTO
    TutorCTR tutorCTR = new TutorCTR(); //Cria um objeto tutorCTR
    
    int gravar_alterar;//Variável usada saber se está alterando ou incluindo
    
    ResultSet rs;//Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_tutor;//Variável para guardar o modelo da tabela
    
    /**
     * Creates new form TutorVIEW
     */
    public TutorVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_tutor = (DefaultTableModel) jtl_consultar_tutor.getModel();
    }
    
    //Método para centralizar o internalFrame
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
    }//Fecha método setPosicao

    //Método utilizado para gravar os dados do tutor
    private void gravar(){
        try{
            tutorDTO.setNome_tutor(nome_tutor.getText());
            tutorDTO.setCpf_tutor(cpf_tutor.getText());
            tutorDTO.setRg_tutor(rg_tutor.getText());
            tutorDTO.setDatanasc_tutor(data_format.parse(datanasc_tutor.getText()));
            tutorDTO.setGenero_tutor(genero_tutor.getSelectedItem().toString());
            tutorDTO.setTel_tutor(tel_tutor.getText());
            tutorDTO.setRua_tutor(rua_tutor.getText());
            tutorDTO.setNum_tutor(num_tutor.getText());
            tutorDTO.setCep_tutor(cep_tutor.getText());
            tutorDTO.setBairro_tutor(bairro_tutor.getText());
            tutorDTO.setCidade_tutor(cidade_tutor.getText());
            tutorDTO.setEstado_tutor(estado_tutor.getSelectedItem().toString());
            
            JOptionPane.showMessageDialog(null, tutorCTR.inserirTutor(tutorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao gravar" + e.getMessage());
        }
    }//Fecha método gravar
    
    //Método utilizado para alterar os dados do tutor
    private void alterar(){
        try{
            tutorDTO.setNome_tutor(nome_tutor.getText());
            tutorDTO.setCpf_tutor(cpf_tutor.getText());
            tutorDTO.setRg_tutor(rg_tutor.getText());
            tutorDTO.setDatanasc_tutor(data_format.parse(datanasc_tutor.getText()));
            tutorDTO.setGenero_tutor(genero_tutor.getSelectedItem().toString());
            tutorDTO.setTel_tutor(tel_tutor.getText());
            tutorDTO.setRua_tutor(rua_tutor.getText());
            tutorDTO.setNum_tutor(num_tutor.getText());
            tutorDTO.setCep_tutor(cep_tutor.getText());
            tutorDTO.setBairro_tutor(bairro_tutor.getText());
            tutorDTO.setCidade_tutor(cidade_tutor.getText());
            tutorDTO.setEstado_tutor(estado_tutor.getSelectedItem().toString());
            
            JOptionPane.showMessageDialog(null, tutorCTR.alterarTutor(tutorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao alterar" + e.getMessage());
        }
    }//Fecha método alterar
    
    //Método utilizado para excluir os dados do tutor
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o tutor?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, tutorCTR.excluirTutor(tutorDTO));
        };
    }//Fecha método excluir
    
    //Método utilizado para liberar/bloquear os campos da tela
    private void liberaCampos(boolean a){
        nome_tutor.setEnabled(a);
        cpf_tutor.setEnabled(a);
        rg_tutor.setEnabled(a);
        datanasc_tutor.setEnabled(a);
        genero_tutor.setEnabled(a);
        tel_tutor.setEnabled(a);
        rua_tutor.setEnabled(a);
        num_tutor.setEnabled(a);
        cep_tutor.setEnabled(a);
        bairro_tutor.setEnabled(a);
        cidade_tutor.setEnabled(a);
        estado_tutor.setEnabled(a);
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
        nome_tutor.setText("");
        cpf_tutor.setText("");
        rg_tutor.setText("");
        datanasc_tutor.setText("");
        tel_tutor.setText("");
        rua_tutor.setText("");
        num_tutor.setText("");
        cep_tutor.setText("");
        bairro_tutor.setText("");
        cidade_tutor.setText("");
    }//Fecha método limpaCampos
    
    //Método utilizado para preencher/construir a jtable
    private void preencheTabela(String nome_tutor){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_tutor.setNumRows(0);
            //Enquanto tiver linhas - faça
            tutorDTO.setNome_tutor(nome_tutor);
            rs = tutorCTR.consultarTutor(tutorDTO, 1); //i = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_tutor.addRow(new Object[]{
                   rs.getString("id_tutor"),
                   rs.getString("nome_tutor"),
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
    
    private void preencheCampos(int id_tutor) {
        try {
            tutorDTO.setId_tutor(id_tutor);
            rs = tutorCTR.consultarTutor(tutorDTO, 2); // 2 = é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();

                nome_tutor.setText(rs.getString("nome_tutor"));
                cpf_tutor.setText(rs.getString("cpf_tutor"));
                rg_tutor.setText(rs.getString("rg_tutor"));

                //datanasc_tutor.setText(rs.getString("datanasc_tutor"));
                datanasc_tutor.setText(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("datanasc_tutor")));

                //genero_tutor.setSelectedItem("Feminino"); // Teste direto

                genero_tutor.setSelectedItem(rs.getString("genero_tutor"));
                tel_tutor.setText(rs.getString("tel_tutor"));
                rua_tutor.setText(rs.getString("rua_tutor"));
                num_tutor.setText(rs.getString("num_tutor"));
                cep_tutor.setText(rs.getString("cep_tutor"));
                bairro_tutor.setText(rs.getString("bairro_tutor"));
                cidade_tutor.setText(rs.getString("cidade_tutor"));
                estado_tutor.setSelectedItem(rs.getString("estado_tutor"));

                gravar_alterar = 2;
                liberaCampos(true);
            }//Fecha if
        }//Fecha try
        catch (Exception erTab) {
            System.out.println("Erro SQL:" + erTab);
        }//Fecha catch
        finally {
            tutorCTR.CloseDB();        
        }//Fecha finally
    }//Fecha método preencheCampos

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nome_tutor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        datanasc_tutor = new javax.swing.JFormattedTextField();
        cpf_tutor = new javax.swing.JFormattedTextField();
        rg_tutor = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tel_tutor = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        rua_tutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        num_tutor = new javax.swing.JTextField();
        cep_tutor = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        bairro_tutor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cidade_tutor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        estado_tutor = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        genero_tutor = new javax.swing.JComboBox<>();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_tutor = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btn_pesquisar = new javax.swing.JButton();
        pesquisa_nome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(700, 400));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CADASTRO DE TUTORES");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nome:");

        try {
            datanasc_tutor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            cpf_tutor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            rg_tutor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        jLabel5.setText("Data de Nascimento:");

        try {
            tel_tutor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Telefone:");

        jLabel7.setText("Rua:");

        jLabel8.setText("Número:");

        try {
            cep_tutor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("CEP:");

        jLabel10.setText("Bairro:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("Estado:");

        estado_tutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jLabel13.setText("Gênero:");

        genero_tutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMININO", "NÃO-BINÁRIO", "HELICÓPTERO DE COMBATE" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(genero_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(datanasc_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nome_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cpf_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rg_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(num_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(bairro_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cidade_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(estado_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tel_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cep_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rua_tutor))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nome_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(genero_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(datanasc_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(rg_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tel_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(cep_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rua_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bairro_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(num_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cidade_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(estado_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jtl_consultar_tutor.getTableHeader().setReorderingAllowed(false);
        jtl_consultar_tutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_tutorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_tutor);
        if (jtl_consultar_tutor.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_tutor.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_tutor.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtl_consultar_tutor.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("CONSULTA");

        btn_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel14.setText("Nome:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(152, 152, 152))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisar)))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesquisar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(426, 426, 426))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(btn_novo)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sair)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo)
                    .addComponent(btn_salvar)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_sair))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        preencheTabela(pesquisa_nome.getText());
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void jtl_consultar_tutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_tutorMouseClicked
        //Pega o ID do tutor selecionado e chama preencheCampos
        preencheCampos(Integer.parseInt(String.valueOf(
                jtl_consultar_tutor.getValueAt(
                jtl_consultar_tutor.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_tutorMouseClicked

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_tutor.setNumRows(0);
        
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_tutor.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_sairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_tutor;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JFormattedTextField cep_tutor;
    private javax.swing.JTextField cidade_tutor;
    private javax.swing.JFormattedTextField cpf_tutor;
    private javax.swing.JFormattedTextField datanasc_tutor;
    private javax.swing.JComboBox<String> estado_tutor;
    private javax.swing.JComboBox<String> genero_tutor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_tutor;
    private javax.swing.JTextField nome_tutor;
    private javax.swing.JTextField num_tutor;
    private javax.swing.JTextField pesquisa_nome;
    private javax.swing.JFormattedTextField rg_tutor;
    private javax.swing.JTextField rua_tutor;
    private javax.swing.JFormattedTextField tel_tutor;
    // End of variables declaration//GEN-END:variables
}
